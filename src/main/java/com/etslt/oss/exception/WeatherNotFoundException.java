package com.etslt.oss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeatherNotFoundException extends RuntimeException {

	public WeatherNotFoundException(String exception) {
		super(exception);
	}

}
