package com.ghali.automation.phase3.tests;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ghali.automation.comparator.Weather;
import com.ghali.automation.comparator.WeatherComparatorWithVariance;
import com.ghali.automation.constants.Constants;
import com.ghali.automation.factory.DriverFactory;
import com.ghali.automation.factory.NdtvPageFactory;
import com.ghali.automation.factory.UtilityFactory;
import com.ghali.automation.interfaces.DriverInterface;
import com.ghali.automation.pages.NdtvHomePage;
import com.ghali.automation.pages.NdtvWeatherPage;
import com.ghali.automation.weatherapihelper.WeatherAPI;


public class WeatherReportComparatorTest extends BaseTest {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherReportComparatorTest.class);

			private DriverInterface driverInterface;
			private NdtvPageFactory ndtvHomePageFactory;
			private NdtvHomePage ndtvHomePage;
			private NdtvWeatherPage ndtvWeatherPage;

			@DataProvider(name = "city-provider1")
			public Object[][] dpMethodCity() {
				return new Object[][] { { "Bhopal" }, { "Bengaluru" } };
			}

			@Test(dataProvider = "city-provider1")
			public void GetWeatherCityTest(String cityName) {

				boolean isTempretueEqual;

				float tempVar;
				LOG.info("Launching Web page");
				try {
					this.driverInterface = DriverFactory.getInstance(Constants.DriverNames.CHROME).getDriverInterface();
				} catch (MalformedURLException | InterruptedException e) {
					LOG.error("Web driver creation failed");
					e.printStackTrace();
				}
				this.ndtvHomePageFactory = new NdtvPageFactory(driverInterface);

				LOG.info("Navigating to Home page");
				ndtvHomePage = this.ndtvHomePageFactory.getNdtvHomePage();
				ndtvHomePage.navigateTo("http://www.ndtv.com");
				UtilityFactory.getJavaUtils().sleep(100);

				LOG.info("Navigating to Weather page");
				ndtvHomePage.ClickoNWeather();
				ndtvWeatherPage = this.ndtvHomePageFactory.getNdtvWeatherPage();
				ndtvWeatherPage.selectCity(cityName);

				// Create Weather object for web
				Weather weatherWeb = new Weather();
				weatherWeb.setHumidity(ndtvWeatherPage.getHumidity());
				weatherWeb.setTempreture(ndtvWeatherPage.getTempretureDeg());
				LOG.info("###Humidity picked from NDTV Weather page " + ndtvWeatherPage.getHumidity());
				LOG.info("#####Tempreture picked from NDTV Weather page " + ndtvWeatherPage.getTempretureDeg());
				LOG.info("Closing the Web Driver");
				this.driverInterface.closeDriver();

				LOG.info("Initating Weather API call..");
				WeatherAPI weatherAPI = new WeatherAPI();
				Map<Object, Object> WeatherApiMap = weatherAPI.GetWeatherApiCity(cityName);

				Weather weatherApi = new Weather();
				tempVar = (float) WeatherApiMap.get("temp") - 273.15F; // Kelvin to deg
				weatherApi.setTempreture(tempVar);
				LOG.info("###Tempreture picked from rest api " + tempVar);
				weatherApi.setHumidity((int) (WeatherApiMap.get("humidity")));
				LOG.info("#### Humidity picked from rest api " + WeatherApiMap.get("humidity"));

				LOG.info("Calling tempreture comparator..");
				WeatherComparatorWithVariance weatherComparator = new WeatherComparatorWithVariance();
				isTempretueEqual = weatherComparator.WeatherComparator(weatherWeb, weatherApi);
				assertEquals(true, isTempretueEqual, "Error :Tempreture are not matching from api and web");

			}

			@DataProvider(name = "city-provider2")
			public Object[][] dpMethodCityState() {
				return new Object[][] { { "Bhopal", "Madya Pradesh" }, { "Bengaluru", "Karnataka" } };
			}

			@Test(dataProvider = "city-provider2")
			public void GetWeatherCityStateTest(String cityName, String stateName) {

				boolean isTempretueEqual;

				float tempVar;
				LOG.info("Launching Web page");
				try {
					this.driverInterface = DriverFactory.getInstance(Constants.DriverNames.CHROME).getDriverInterface();
				} catch (MalformedURLException | InterruptedException e) {
					LOG.error("Web driver creation failed");
					e.printStackTrace();
				}
				this.ndtvHomePageFactory = new NdtvPageFactory(driverInterface);

				LOG.info("Navigating to Home page");
				ndtvHomePage = this.ndtvHomePageFactory.getNdtvHomePage();
				ndtvHomePage.navigateTo("http://www.ndtv.com");
				UtilityFactory.getJavaUtils().sleep(100);

				LOG.info("Navigating to Weather page");
				ndtvHomePage.ClickoNWeather();
				ndtvWeatherPage = this.ndtvHomePageFactory.getNdtvWeatherPage();
				ndtvWeatherPage.selectCity(cityName);

				// Create Weather object for web
				Weather weatherWeb = new Weather();
				weatherWeb.setHumidity(ndtvWeatherPage.getHumidity());
				weatherWeb.setTempreture(ndtvWeatherPage.getTempretureDeg());
				LOG.info("###Humidity picked from NDTV Weather page " + ndtvWeatherPage.getHumidity());
				LOG.info("#####Tempreture picked from NDTV Weather page " + ndtvWeatherPage.getTempretureDeg());
				LOG.info("Closing the Web Driver");
				this.driverInterface.closeDriver();

				LOG.info("Initating Weather API call..");
				WeatherAPI weatherAPI = new WeatherAPI();
				Map<Object, Object> WeatherApiMap = weatherAPI.GetWeatherApiCity(cityName, stateName);

				Weather weatherApi = new Weather();
				tempVar = (float) WeatherApiMap.get("temp") - 273.15F; // Kelvin to deg
				weatherApi.setTempreture(tempVar);
				LOG.info("###Tempreture picked from rest api " + tempVar);
				weatherApi.setHumidity((int) (WeatherApiMap.get("humidity")));
				LOG.info("#### Humidity picked from rest api " + WeatherApiMap.get("humidity"));

				LOG.info("Calling tempreture comparator..");
				WeatherComparatorWithVariance weatherComparator = new WeatherComparatorWithVariance();
				isTempretueEqual = weatherComparator.WeatherComparator(weatherWeb, weatherApi);
				assertEquals(true, isTempretueEqual, "Error :Tempreture are not matching from api and web");

			}

			@DataProvider(name = "city-provider3")
			public Object[][] dpMethodCityStatePinCode() {
				return new Object[][] { { "Bhopal", "Madya Pradesh", "462001" },
						{ "Bengaluru", "Karnataka", "560001" } };
			}

			@Test(dataProvider = "city-provider3")
			public void GetWeatherCityStatePinCodeTest(String cityName, String stateName, String pinCode) {

				boolean isTempretueEqual;

				float tempVar;
				LOG.info("Launching Web page");
				try {
					this.driverInterface = DriverFactory.getInstance(Constants.DriverNames.CHROME).getDriverInterface();
				} catch (MalformedURLException | InterruptedException e) {
					LOG.error("Web driver creation failed");
					e.printStackTrace();
				}
				this.ndtvHomePageFactory = new NdtvPageFactory(driverInterface);

				LOG.info("Navigating to Home page");
				ndtvHomePage = this.ndtvHomePageFactory.getNdtvHomePage();
				ndtvHomePage.navigateTo("http://www.ndtv.com");
				UtilityFactory.getJavaUtils().sleep(100);

				LOG.info("Navigating to Weather page");
				ndtvHomePage.ClickoNWeather();
				ndtvWeatherPage = this.ndtvHomePageFactory.getNdtvWeatherPage();
				ndtvWeatherPage.selectCity(cityName);

				// Create Weather object for web
				Weather weatherWeb = new Weather();
				weatherWeb.setHumidity(ndtvWeatherPage.getHumidity());
				weatherWeb.setTempreture(ndtvWeatherPage.getTempretureDeg());
				LOG.info("###Humidity picked from NDTV Weather page " + ndtvWeatherPage.getHumidity());
				LOG.info("#####Tempreture picked from NDTV Weather page " + ndtvWeatherPage.getTempretureDeg());
				LOG.info("Closing the Web Driver");
				this.driverInterface.closeDriver();

				LOG.info("Initating Weather API call..");
				WeatherAPI weatherAPI = new WeatherAPI();
				Map<Object, Object> WeatherApiMap = weatherAPI.GetWeatherApiCity(cityName, stateName, pinCode);

				Weather weatherApi = new Weather();
				tempVar = (float) WeatherApiMap.get("temp") - 273.15F; // Kelvin to deg
				weatherApi.setTempreture(tempVar);
				LOG.info("###Tempreture picked from rest api " + tempVar);
				weatherApi.setHumidity((int) (WeatherApiMap.get("humidity")));
				LOG.info("#### Humidity picked from rest api " + WeatherApiMap.get("humidity"));

				LOG.info("Calling tempreture comparator..");
				WeatherComparatorWithVariance weatherComparator = new WeatherComparatorWithVariance();
				isTempretueEqual = weatherComparator.WeatherComparator(weatherWeb, weatherApi);
				assertEquals(true, isTempretueEqual, "Error :Tempreture are not matching from api and web");

			}
}
