package com.etslt.oss.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(value = WeatherAPIService.class)
public class WeatherAPIServiceTests {

//	@Autowired
//	MockMvc mockMvc;
//	
//	@MockBean
//	WeatherAPIService service;
//	
	@Test
	public void registerUser() throws Exception {
		fail();
//		String URI = "/api/weather/v1/city/london";
//		String city = "London";
//		String country = "uk";
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		mockMvc.perform(requestBuilder).andReturn();
//		mockMvc.perform(requestBuilder).andReturn();
//		mockMvc.perform(requestBuilder).andReturn();
//
//        verify(service, times(1));
//
	}
}
