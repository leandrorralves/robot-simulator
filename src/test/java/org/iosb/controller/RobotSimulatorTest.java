package org.iosb.controller;

import org.iosb.util.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotSimulatorTest {

    private final String RESOURCES_DIRECTORY = "src/test/resources/";

    @Test
    void testExample1() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "test1.txt");
        robotSimulator.testMovements();

        assertEquals(0, robotSimulator.getRobot().getPosX());
        assertEquals(5, robotSimulator.getRobot().getPosY());
        assertEquals(Orientation.E, robotSimulator.getRobot().getOrientation());
    }

    @Test
    void testExample2() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "test2.txt");
        robotSimulator.testMovements();

        assertEquals(3, robotSimulator.getRobot().getPosX());
        assertEquals(1, robotSimulator.getRobot().getPosY());
        assertEquals(Orientation.S, robotSimulator.getRobot().getOrientation());
    }

    @Test
    void testExample3() {
        RobotSimulator robotSimulator = new RobotSimulator(RESOURCES_DIRECTORY + "test3.txt");
        robotSimulator.testMovements();

        assertEquals(3, robotSimulator.getRobot().getPosX());
        assertEquals(0, robotSimulator.getRobot().getPosY());
        assertEquals(Orientation.W, robotSimulator.getRobot().getOrientation());
    }
}