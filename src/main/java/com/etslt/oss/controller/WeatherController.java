package com.etslt.oss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etslt.oss.dto.WeatherResponse;
import com.etslt.oss.exception.CityNotAvailableException;
import com.etslt.oss.exception.CoordNotAvailableException;
import com.etslt.oss.exception.InvalidParamException;
import com.etslt.oss.exception.WeatherNotFoundException;
import com.etslt.oss.service.WeatherAPIService;

@RestController
@RequestMapping("/api")
public class WeatherController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WeatherAPIService weatherService;


	@GetMapping("/v1/weather")
	public ResponseEntity getWeather(@RequestParam(value = "city", required = false) String city, 
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "lat", required = false) String lattitude,
			@RequestParam(value = "lon", required = false) String longitude,
			@RequestParam(value = "type", required = false) String queryType,
			@RequestParam(value = "isCacheable", required = false) Boolean isCacheable) {
		
		WeatherResponse weather = null;
		
		if (null == queryType) {
			logger.debug("type parameter is mandatory");
			throw new InvalidParamException("type parameter is mandatory");
		} else if (!QueryType.contains(queryType)) {
			logger.debug("Invalid request parameters - queryType");
			throw new InvalidParamException("Invalid type parameter(Allowed values are BY_CITY, BY_GEO). ");
		}
			
		else if (queryType.equalsIgnoreCase(QueryType.BY_CITY.value()))
			if (null == city || null == country) 	
				throw new CityNotAvailableException("Invalid City or Country parameters");
			else
				weather = weatherService.getWeatherByCity(city, country, true);
		
		else if (queryType.equalsIgnoreCase(QueryType.BY_GEO.value()))
			if (null == lattitude || null == longitude) 	
				throw new CoordNotAvailableException("Invalid Lattitude or Longitude parameters");
			else
				weather = weatherService.getWeatherByGeoLocation(lattitude, longitude, true);
		else {
			logger.debug("Invalid request parameters");
			return new ResponseEntity<WeatherResponse>(weather, HttpStatus.BAD_REQUEST);
		}
			
		if (null == weather)
			throw new WeatherNotFoundException("Weather details doesn't exist for the requested parameters");
		
		return ResponseEntity.ok().body(weather);
	}
	
}


enum QueryType{
	BY_CITY("BY_CITY"), BY_GEO("BY_GEO");
	
	private QueryType(String value) {
		this.value = value;
	}
	String value;
	
	public String value() {
		return value;
	}
	
	public static boolean contains(String test) {

	    for (QueryType c : QueryType.values()) {
	        if (c.name().equalsIgnoreCase(test)) {
	            return true;
	        }
	    }
	    return false;
	}
}
