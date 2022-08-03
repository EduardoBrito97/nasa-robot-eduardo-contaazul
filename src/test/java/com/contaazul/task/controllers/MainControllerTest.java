package com.contaazul.task.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

	@Autowired
	private MockMvc mvc;

    public static String[][] validCommandsAndResponses() {
        return new String[][] {
            {"MMRMMRMM", "(2, 0, S)"},
            {"MML", "(0, 2, W)"}
        };
    }

	@ParameterizedTest
    @MethodSource(value = "validCommandsAndResponses")
	public void testCommandRobotWithValidInputShouldReturnStatusOkAndCorrectResponse(String command, String response) throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/rest/mars/" + command).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(response)));
	}

    public static String[][] problematicCommandsAndResponses() {
        return new String[][] {
            {"AAA", "'A' is not a valid command!"},
            {"MMMMMMMMMMMMMMMMMMMMMMMM", "The robot fell out of the terrain."}
        };
    }

	@ParameterizedTest
    @MethodSource(value = "problematicCommandsAndResponses")
	public void testCommandRobotWithInvalidInputShouldReturnStatusBadRequestAndCorrectError(String command, String response) throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/rest/mars/" + command).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(equalTo(response)));
	}
}