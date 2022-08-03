package com.contaazul.task.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contaazul.task.entities.Robot;
import com.contaazul.task.services.RobotService;

@RestController
public class MainController {

	@PostMapping(path = "/rest/mars/{commands}")
	public ResponseEntity<String> commandRobot(@PathVariable String commands) {
		Robot robot = new Robot();

		try {
			for (int i = 0; i < commands.length(); i++) {
				char command = commands.charAt(i);
				if (command == 'M' || command == 'm') {
					RobotService.move(robot);
				} else {
					RobotService.rotate(command, robot);
				}
			}
			return ResponseEntity.ok(robot.getPositionTuple());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
