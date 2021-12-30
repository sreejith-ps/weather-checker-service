package com.etslt.oss.exception;

import java.util.Date;

import javax.naming.ServiceUnavailableException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus.Series;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.etslt.oss.dto.ErrorResponse;

@ControllerAdvice
@RestController
public class WeatherExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ErrorResponse exceptionResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
    		ex.getMessage(), new Date());
    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(WeatherNotFoundException.class)
  public final ResponseEntity<Object> handleWeatherNotFoundException(WeatherNotFoundException ex, WebRequest request) {
	    ErrorResponse exceptionResponse = new ErrorResponse(CustomStatus.WEATHER_NOT_AVAILABLE.value(), ex.getMessage(),
	            new Date());
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(CityNotAvailableException.class)
  public final ResponseEntity<Object> handleCityNotAvailableException(CityNotAvailableException ex, WebRequest request) {
	    ErrorResponse exceptionResponse = new ErrorResponse(CustomStatus.CITY_NOT_AVAILABLE.value(), ex.getMessage(),
	            new Date());
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  
  @ExceptionHandler(CoordNotAvailableException.class)
  public final ResponseEntity<Object> handleCoordNotAvailableException(CoordNotAvailableException ex, WebRequest request) {
	    ErrorResponse exceptionResponse = new ErrorResponse(CustomStatus.LAT_NOT_AVAILABLE.value(), ex.getMessage(),
	            new Date());
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidParamException.class)
  public final ResponseEntity<Object> handleCoordNotAvailableException(InvalidParamException ex, WebRequest request) {
	    ErrorResponse exceptionResponse = new ErrorResponse(CustomStatus.INVALID_QUERY_TYPE.value(), ex.getMessage(),
	            new Date());
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
	    ErrorResponse exceptionResponse = new ErrorResponse(400,
	            ex.getBindingResult().toString(), new Date());
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  } 
  

  @ResponseStatus(
          value = HttpStatus.GATEWAY_TIMEOUT,
          reason = "Weather Service Not Responding, Try Again")
  @ExceptionHandler(ServiceUnavailableException.class)
  public void handleException(ServiceUnavailableException e) {
	  
  }
  

  @ExceptionHandler(UserAlreadyExistsException.class)
  public final ResponseEntity<Object> handleUserExistsException(UserAlreadyExistsException ex, WebRequest request) {
	    ErrorResponse exceptionResponse = new ErrorResponse(CustomStatus.USER_EXISTS.value(), ex.getMessage(),
	            new Date());
    return new ResponseEntity(exceptionResponse, HttpStatus.FOUND);
  }
  
}
enum CustomStatus {
	
	CITY_NOT_AVAILABLE(151, "City Not Available"),
	COUNTRY_NOT_AVAILABLE(152, "Country Not Available"),
	LAT_NOT_AVAILABLE(153, "Lattitude or Longitude Not Available"),
	WEATHER_NOT_AVAILABLE(154, "Weather Not Available"),
	INVALID_QUERY_TYPE(155, "Invalid Query Type"),
	USER_EXISTS(156, "User already exists with same name");
	
	private final int value;

	private final String reasonPhrase;

	CustomStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
}