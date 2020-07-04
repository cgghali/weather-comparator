package com.ghali.automation.weatherapihelper;


import java.util.Map;

import com.ghali.automation.factory.UtilityFactory;
import com.jayway.restassured.response.Response;

public class WeatherAPI {

	public WeatherAPI() {

	}

	/**
	 * Returns the value selected in the temperature in deg.
	 *
	 * @param String
	 * @return Float
	 */
	public Map<Object, Object> GetWeatherApiCity(String cityName) {
		Response response;
		response = UtilityFactory.getRestApiUtils().get(cityName);
		Map<Object, Object> mainResponse = response.jsonPath().getMap("main");
		System.out.println(mainResponse);
		return mainResponse;

	}

	/**
	 * Returns the value selected in the temperature in deg.
	 *
	 * @param String,String
	 * @return Map
	 */
	public Map<Object, Object> GetWeatherApiCity(String cityName, String state) {
		Response response;
		response = UtilityFactory.getRestApiUtils().get(cityName);
		Map<Object, Object> mainResponse = response.jsonPath().getMap("main");
		System.out.println(mainResponse);
		return mainResponse;

	}

	/**
	 * Returns the value selected in the temperature in deg.
	 *
	 * @param String,String,String
	 * @return Map
	 */
	public Map<Object, Object> GetWeatherApiCity(String cityName, String state, String countryCode) {
		Response response;
		response = UtilityFactory.getRestApiUtils().get(cityName);
		Map<Object, Object> mainResponse = response.jsonPath().getMap("main");
		System.out.println(mainResponse);
		return mainResponse;
	}

}
