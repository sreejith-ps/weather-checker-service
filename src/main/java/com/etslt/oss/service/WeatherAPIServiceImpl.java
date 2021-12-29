package com.etslt.oss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.etslt.oss.dto.WeatherResponse;

@Service
public class WeatherAPIServiceImpl implements WeatherAPIService {

	private static final String API_KEY = "cd54b7fbdaf08c6e9677d93bfb7a35b2";
//	private static final String WEATHER_BY_CITY = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
//	private static final String WEATHER_BY_CITY = "https://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=cd54b7fbdaf08c6e9677d93bfb7a35b2";
	private static final String WEATHER_BY_CITY = "https://api.openweathermap.org/data/2.5/weather?";
//	Params:
//		q= city name and country code separated by comma, use ISO 3166 country codes
//		API-KEY = "cd54b7fbdaf08c6e9677d93bfb7a35b2"

//	private static final String WEATHER_BY_GEOLOC = "api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appkey={API key}";
	private static final String WEATHER_BY_GEOLOC = "https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=cd54b7fbdaf08c6e9677d93bfb7a35b2";
//	Params:
//		lat, lon coordinates of the location of your interest
//		API-KEY = "cd54b7fbdaf08c6e9677d93bfb7a35b2"
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
//	@Cacheable(value = "city-weather", key = "'CityWeather'+#city + #country")
	@Cacheable(value = "ten-minute-cache", key = "'CityWeather'+#city + #country" , condition = "#isCacheable != null && #isCacheable")
	public WeatherResponse getWeatherByCity(String city, String country, Boolean isCacheable) {
		System.out.println("Weather by city = " + city + " and country - " + country);
		String uri = WEATHER_BY_CITY + "q=" + city + "," + country + "&appid=" + API_KEY;
		WeatherResponse weather = restTemplate.getForObject(uri, WeatherResponse.class);
		return weather;
	}

	@Override
	@Cacheable(value = "geo-weather", key = "'GeoWeather'+#lat + #lon")
	public WeatherResponse getWeatherByGeoLocation(String lat, String lon, Boolean isCacheable) {
		String uri = WEATHER_BY_GEOLOC;
		WeatherResponse weather = restTemplate.getForObject(uri, WeatherResponse.class);
		return weather;
	}
}
