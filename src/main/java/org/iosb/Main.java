package org.iosb;

import org.iosb.controller.RobotSimulator;

public class Main {
    public static void main(String[] args) {

        String fileName = args[0] != null ? args[0] : "src/main/resources/test.txt";

        RobotSimulator robotSimulator = new RobotSimulator(fileName);
        robotSimulator.testMovements();

    }
}