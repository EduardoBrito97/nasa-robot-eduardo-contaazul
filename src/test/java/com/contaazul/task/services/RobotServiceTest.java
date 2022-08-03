package com.contaazul.task.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.contaazul.task.entities.Robot;

public class RobotServiceTest {

    private void rotateRobotNTimes(int N, char com, Robot robot) {
        IntStream.range(0, N)
                .forEach(repeat -> { 
                    RobotService.rotate(com, robot);
                });
    }

    @Test
    public void testRotateValidCommandsShouldUpdateRobotAngle() {
        Robot robot = new Robot();

        // The robot start pointing north (angle = 90), thus it should become 0
        RobotService.rotate('R', robot);
        assertEquals(0, robot.getAngle());

        // Since there is no need to update the angle now, it should be negative now
        rotateRobotNTimes(3, 'R', robot);
        assertEquals(-270, robot.getAngle());

        // We've turned left 4 times, thus it should go back to starting angle
        rotateRobotNTimes(4, 'L', robot);
        assertEquals(90, robot.getAngle());

        // Lowercase commands should work too
        rotateRobotNTimes(2, 'l', robot);
        assertEquals(270, robot.getAngle());

        // Ofc also for r command
        rotateRobotNTimes(1, 'r', robot);
        assertEquals(180, robot.getAngle());
    }

    @Test()
    public void testRotateInvalidCommandShouldThrowExceptionWithDescriptiveMessage(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RobotService.rotate('a', new Robot());
        });
    
        String expectedMessage = "'a' is not a valid command!";
        String actualMessage = exception.getMessage();
    
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    // This way it will fall of terrain through negative/positive x/y
    @ValueSource(ints = {0, 1, 2, 3})
    public void testMoveUntilRobotIsOutOfTerrainShouldThrowExceptionWithDescriptiveMessage(int turnLeftNum){
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Robot robot = new Robot();
            rotateRobotNTimes(turnLeftNum, 'L', robot);
            IntStream.range(0, 6)
                    .forEach(repeat -> { 
                        RobotService.move(robot);
                    });
        });
    
        String expectedMessage = "The robot fell out of the terrain.";
        String actualMessage = exception.getMessage();
    
        assertEquals(expectedMessage, actualMessage);
    }
}
