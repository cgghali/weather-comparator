package com.ghali.automation.phase1.tests;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ghali.automation.comparator.TemperatureComparatorWithVariance;
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

				float tempretureWeb;
				float tempretureApi;
				boolean isTempretueEqual = false;
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
				tempretureWeb = ndtvWeatherPage.getTempreture(cityName);

				LOG.info("Closing the Web Driver");
				this.driverInterface.closeDriver();

				LOG.info("Initating Weather API call..");
				WeatherAPI api = new WeatherAPI();
				tempretureApi = api.GetWeatherApiCity(cityName);

				LOG.info("Calling tempreture comparator..");
				TemperatureComparatorWithVariance weatherComparator = new TemperatureComparatorWithVariance();
				isTempretueEqual = weatherComparator.TemperatureComparator(tempretureWeb, tempretureApi);
				assertEquals(true, isTempretueEqual, "Error :Tempreture are not matching from api and web");

			}

			@DataProvider(name = "city-provider2")
			public Object[][] dpMethodCityState() {
				return new Object[][] { { "Bhopal", "Madhya Pradesh" }, { "Bengaluru", "Karnataka" } };
			}

			@Test(dataProvider = "city-provider2")
			public void GetWeatherCityStateTest(String cityName, String stateName) {

				float tempretureWeb;
				float tempretureApi;
				boolean isTempretueEqual = false;
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
				tempretureWeb = ndtvWeatherPage.getTempreture(cityName);

				LOG.info("Closing the Web Driver");
				this.driverInterface.closeDriver();

				LOG.info("Initating Weather API call..");
				WeatherAPI api = new WeatherAPI();
				tempretureApi = api.GetWeatherApiCity(cityName, stateName);

				LOG.info("Calling tempreture comparator..");
				TemperatureComparatorWithVariance weatherComparator = new TemperatureComparatorWithVariance();
				isTempretueEqual = weatherComparator.TemperatureComparator(tempretureWeb, tempretureApi);
				assertEquals(true, isTempretueEqual, "Error :Tempreture are not matching from api and web");

			}

			@DataProvider(name = "city-provider3")
			public Object[][] dpMethodCityStatePin() {
				return new Object[][] { { "Bhopal", "Madhya Pradesh", "462001" },
						{ "Bengaluru", "Karnataka", "560001" } };
			}

			@Test(dataProvider = "city-provider3")
			public void GetWeatherCityStatePinCodeTest(String cityName, String stateName, String pinCode) {

				float tempretureWeb;
				float tempretureApi;
				boolean isTempretueEqual = false;
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
				tempretureWeb = ndtvWeatherPage.getTempreture(cityName);

				LOG.info("Closing the Web Driver");
				this.driverInterface.closeDriver();

				LOG.info("Initating Weather API call..");
				WeatherAPI api = new WeatherAPI();
				tempretureApi = api.GetWeatherApiCity(cityName, stateName, pinCode);

				LOG.info("Calling tempreture comparator..");
				TemperatureComparatorWithVariance weatherComparator = new TemperatureComparatorWithVariance();
				isTempretueEqual = weatherComparator.TemperatureComparator(tempretureWeb, tempretureApi);
				assertEquals(true, isTempretueEqual, "Error :Tempreture are not matching from api and web");

			}

}
