package org.iosb;

import org.apache.commons.lang3.ObjectUtils;
import org.iosb.controller.RobotSimulator;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        if (args.length > 0 && ObjectUtils.isNotEmpty(args[0])) {
            if (isDataFileCreated(args[0])) {
                RobotSimulator robotSimulator = new RobotSimulator(args[0]);
                robotSimulator.executeSimulation();
            } else {
                System.out.print("Input file does not exist!");
            }
        } else {
            System.out.print("To run this program you must provide a file, see project README.md for more details.");
        }
    }

    private static boolean isDataFileCreated(String fileName) {
        File dataFile = new File(fileName);
        return dataFile.exists();
    }
}