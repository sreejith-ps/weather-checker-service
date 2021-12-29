package com.etslt.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.etslt.oss.dto.WeatherResponse;
import com.etslt.oss.service.WeatherAPIService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
	@Autowired
	WeatherAPIService weatherService;

	@GetMapping("/v1/city/{code}")
	public ResponseEntity getWeatherByCity(@PathVariable("code") String code) {
		
//		if (null == code)
//			throw new Exception("Bad request");
//		
//		String[]  params = code.split(",");
//		
//		if (params.length < 2)
//			throw new Exception("Bad request");
		
		String city = "London";
		String country = "uk";
			
		WeatherResponse weather = weatherService.getWeatherByCity(city, country, true);
		return ResponseEntity.ok().body(weather);
	}
	
	@GetMapping("/v1/geography/{lat}")
	public ResponseEntity<WeatherResponse> getWeatherByGeoLocation() {
		String lattitude = "35";
		String longitude = "139";
		WeatherResponse weather = weatherService.getWeatherByGeoLocation(lattitude, longitude, true);
		return ResponseEntity.ok().body(weather);
	}
}
