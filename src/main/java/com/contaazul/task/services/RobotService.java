package com.contaazul.task.services;

import com.contaazul.task.entities.Robot;

public class RobotService {

    public static void move(Robot robot) {
        char orientation = robot.getOrientation();
        Integer coordinateX = robot.getCoordinateX();
        Integer coordinateY = robot.getCoordinateY();

        switch (orientation) {
            case 'E':
                coordinateX += 1;
                System.out.println("The robot is moving east! (X + 1)");
                break;
            case 'N':
                coordinateY += 1;
                System.out.println("The robot is moving north! (Y + 1)");
                break;
            case 'W':
                coordinateX -= 1;
                System.out.println("The robot is moving west! (X - 1)");
                break;
            case 'S':
                coordinateY -= 1;
                System.out.println("The robot is moving south! (Y - 1)");
                break;
        }

        if (coordinateX < 0 || coordinateY < 0 || coordinateX > 4 || coordinateY > 4){
            throw new IllegalStateException("The robot fell out of the terrain.");
        }
        robot.setCoordinateX(coordinateX);
        robot.setCoordinateY(coordinateY);
    }

    public static void rotate(char command, Robot robot) {
        Integer angle = robot.getAngle();
        switch(command){
            case 'r':
            case 'R':
                angle -= 90;
                System.out.println("The robot rotated right!");
                break;
            case 'l':
            case 'L':
                angle += 90;
                System.out.println("The robot rotated left!");
                break;
            default:
                throw new IllegalArgumentException("'" + command + "' is not a valid command!");
        }

        robot.setAngle(angle);
    }    
}
