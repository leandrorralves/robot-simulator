package org.iosb.controller;

import org.iosb.BusinessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RobotSimulatorTest {

    private final String RESOURCES_DIRECTORY = "src/test/resources/";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void executeSimulation_validData1() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "test1.txt");
        robotSimulator.executeSimulation();

        assertEquals("0 5 E", outContent.toString());
    }

    @Test
    void executeSimulation_validData2() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "test2.txt");
        robotSimulator.executeSimulation();

        assertEquals("3 1 S", outContent.toString());
    }

    @Test
    void executeSimulation_InvalidFileData_ExceptionThrow() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "error/invalid_table_data.txt");

        BusinessException thrown = assertThrows(
                BusinessException.class,
                robotSimulator::executeSimulation,
                "Expected executeSimulation() to throw, but it didn't"
        );

        assertEquals("Invalid file data!", thrown.getMessage());
    }

    @Test
    void executeSimulation_InvalidRobotInitialPosition_ExceptionThrow() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "error/invalid_robot_initial_position.txt");

        BusinessException thrown = assertThrows(
                BusinessException.class,
                robotSimulator::executeSimulation,
                "Expected executeSimulation() to throw, but it didn't"
        );

        assertEquals("Robot's initial position is invalid!", thrown.getMessage());
    }

    @Test
    void executeSimulation_MovementNotAllowed_ExceptionThrow() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "error/out_of_bounds.txt");

        BusinessException thrown = assertThrows(
                BusinessException.class,
                robotSimulator::executeSimulation,
                "Expected executeSimulation() to throw, but it didn't"
        );

        assertEquals("Robot has reached at the table's end while moving forward!", thrown.getMessage());
    }
}