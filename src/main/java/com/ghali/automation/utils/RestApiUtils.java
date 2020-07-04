package com.ghali.automation.utils;

import static com.jayway.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ghali.automation.constants.Constants;
import com.jayway.restassured.response.Response;

public class RestApiUtils {

	private static final Logger LOG = LoggerFactory.getLogger(RestApiUtils.class);

	public RestApiUtils() {

		System.setProperty("jsse.enableSNIExtension", "false");
	}

	Response response;

	public Response get(String cityName) {

		final String API_KEY = "7fe67bf08c80ded756e598d6f8fedaea";
		String restAPIUrl = Constants.WeatherApiEndpoints.BASE_URL + cityName + "&" + "appid=" + API_KEY;
		LOG.debug(String.format("Performing the get weather call on api : %s", restAPIUrl));
		Response response = given().get(restAPIUrl);
		LOG.debug(
				String.format("Get call on url %s took %s seconds", restAPIUrl, response.getTimeIn(TimeUnit.SECONDS)));

		return response;
	}

	public Response get(String cityName, String stateName) {

		final String API_KEY = "7fe67bf08c80ded756e598d6f8fedaea";
		String restAPIUrl = Constants.WeatherApiEndpoints.BASE_URL + cityName + "," + stateName + "&" + "appid="
				+ API_KEY;
		LOG.debug(String.format("Performing the get weather call on api : %s", restAPIUrl));
		Response response = given().get(restAPIUrl);
		LOG.debug(
				String.format("Get call on url %s took %s seconds", restAPIUrl, response.getTimeIn(TimeUnit.SECONDS)));

		return response;
	}

	public Response get(String cityName, String stateName, String pinCode) {

		final String API_KEY = "7fe67bf08c80ded756e598d6f8fedaea";
		String restAPIUrl = Constants.WeatherApiEndpoints.BASE_URL + cityName + "," + stateName + "," + pinCode + "&"
				+ "appid=" + API_KEY;
		LOG.debug(String.format("Performing the get weather call on api : %s", restAPIUrl));
		Response response = given().get(restAPIUrl);
		LOG.debug(
				String.format("Get call on url %s took %s seconds", restAPIUrl, response.getTimeIn(TimeUnit.SECONDS)));

		return response;
	}
}
