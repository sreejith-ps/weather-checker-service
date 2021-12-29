package com.etslt.oss.service;

import com.etslt.oss.dto.WeatherResponse;

public interface WeatherAPIService {
	public WeatherResponse getWeatherByCity(String city, String country);
	public WeatherResponse getWeatherByGeoLocation(String lat, String lon);
}