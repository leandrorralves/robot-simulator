package org.iosb.controller;

import lombok.Getter;
import org.iosb.BusinessException;
import org.iosb.model.Robot;
import org.iosb.util.Orientation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Getter
public class RobotSimulator {

    private final Robot robot = new Robot();
    private final String fileName;

    private int tableRowSize;
    private int tableColumnSize;

    private char[] movementCommands;

    public RobotSimulator(String fileName) {
        this.fileName = fileName;
    }

    public void executeSimulation() {

        readDataFromFile();
        executeRobotCommands();
        System.out.print(robot.getPosition());

    }

    private void readDataFromFile() {

        String tableSizeLine;
        String initialPositionLine;
        String commandsLine;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            tableSizeLine = reader.readLine();
            initialPositionLine = reader.readLine();
            commandsLine = reader.readLine();

            reader.close();
        } catch (IOException e) {
            throw new BusinessException("Error while reading file: " + fileName);
        }

        setDataFromFile(tableSizeLine, initialPositionLine, commandsLine);
    }

    private void setDataFromFile(String tableSizeLine, String initialPositionLine, String commandsLine) {

        if (isValidFile(tableSizeLine, initialPositionLine, commandsLine)) {

            tableRowSize = Integer.parseInt(tableSizeLine.split("\\s+")[0]);
            tableColumnSize = Integer.parseInt(tableSizeLine.split("\\s+")[1]);

            setRobotInitialPosition(Integer.parseInt(initialPositionLine.split("\\s+")[0]),
                    Integer.parseInt(initialPositionLine.split("\\s+")[1]),
                    Orientation.valueOf(initialPositionLine.split("\\s+")[2]));

            movementCommands = commandsLine.toCharArray();

        } else {
            throw new BusinessException("Invalid file data!");
        }
    }

    private boolean isValidFile(String tableSizeLine, String initialPositionLine, String commandsLine) {

        final String tableSizeRegex = "^\\d+\\s\\d+$";
        final String initialPositionRegex = "^\\d+\\s\\d+\\s[NESW]$";
        final String commandsLineRegex = "^[MLR]+$";

        return tableSizeLine != null && tableSizeLine.matches(tableSizeRegex) &&
                initialPositionLine != null && initialPositionLine.matches(initialPositionRegex) &&
                commandsLine != null && commandsLine.matches(commandsLineRegex);
    }

    private void setRobotInitialPosition(int rowNumber, int columnNumber, Orientation orientation) {
        if (rowNumber >= 0 && rowNumber < tableRowSize && columnNumber >= 0 && columnNumber < tableColumnSize) {
            robot.setRowPosition(rowNumber);
            robot.setColumnPosition(columnNumber);
            robot.setOrientation(orientation);
        } else {
            throw new BusinessException("Robot's initial position is invalid!");
        }
    }

    private void executeRobotCommands() {
        for (char movementCommand : movementCommands) {
            validateMovement(movementCommand);
            robot.move(movementCommand);
        }
    }

    private void validateMovement(char movementCommand) {
        if (movementCommand == 'M' &&
                ((Orientation.N.equals(robot.getOrientation()) && robot.getRowPosition() == 0) ||
                 (Orientation.W.equals(robot.getOrientation()) && robot.getColumnPosition() == 0) ||
                 (Orientation.S.equals(robot.getOrientation()) && robot.getRowPosition() == tableRowSize - 1) ||
                 (Orientation.E.equals(robot.getOrientation()) && robot.getColumnPosition() == tableColumnSize - 1))) {
            throw new BusinessException("Robot has reached at the table's end while moving forward!");
        }
    }
}