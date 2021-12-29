package com.etslt.oss.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.awt.print.Book;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.etslt.oss.entity.User;
import com.etslt.oss.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = UserController.class)
public class UserControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Test
	public void registerUser() throws Exception {
		User mockUser = new User(1L, "Sreejith", "sreejith@abc.com", "abc@123");
		String input = getJson(mockUser);
		String URI = "/api/users/v1";
		when(userService.registerUser(Mockito.any(User.class))).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(input)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String output = response.getContentAsString();
		assertThat(output).isEqualTo(input);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	private String getJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
