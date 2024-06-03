package org.iosb.controller;

import lombok.Getter;
import org.iosb.BusinessException;
import org.iosb.model.Robot;
import org.iosb.model.TestData;
import org.iosb.util.Orientation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Getter
public class RobotSimulator {

    private final Robot robot = new Robot();
    private final String fileName;

    public RobotSimulator(String fileName) {
        this.fileName = fileName;
    }

    private TestData parseTestData() {

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

        TestData testData = new TestData();

        if (isValidFile(tableSizeLine, initialPositionLine, commandsLine)) {

            testData.setTableSizeX(Integer.parseInt(tableSizeLine.split("\\s+")[0]));
            testData.setTableSizeY(Integer.parseInt(tableSizeLine.split("\\s+")[1]));

            setRobotInitialPosition(Integer.parseInt(initialPositionLine.split("\\s+")[0]),
                    Integer.parseInt(initialPositionLine.split("\\s+")[1]),
                    Orientation.valueOf(initialPositionLine.split("\\s+")[2]));

            testData.setMovementCommands(commandsLine.toCharArray());
        } else {
            throw new BusinessException("Invalid file data!");
        }

        return testData;
    }

    //todo: split into 3 validations with each bexception by line
    private boolean isValidFile(String tableSizeLine, String initialPositionLine, String commandsLine) {

        final String tableSizeRegex = "^\\d+\\s\\d+$";
        final String initialPositionRegex = "^\\d+\\s\\d+\\s[NESW]$";
        final String commandsLineRegex = "^[MLR]+$";

        return tableSizeLine != null && tableSizeLine.matches(tableSizeRegex) &&
                initialPositionLine != null && initialPositionLine.matches(initialPositionRegex) &&
                commandsLine != null && commandsLine.matches(commandsLineRegex);
    }

    private void setRobotInitialPosition(int posX, int posY, Orientation orientation) {
        //TODO: test boundaries
        robot.setPosX(posX);
        robot.setPosY(posY);
        robot.setOrientation(orientation);
    }

    public void testMovements() {

        TestData testData = parseTestData();

        // test movements
        for (char movementCommand : testData.getMovementCommands()) {
            robot.move(movementCommand);
        }

        //print final position
        System.out.println(robot.getPosX() + " " + robot.getPosY() + " " + robot.getOrientation().name());
    }

}
