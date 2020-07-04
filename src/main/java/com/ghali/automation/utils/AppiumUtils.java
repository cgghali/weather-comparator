package com.ghali.automation.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.testng.Assert;

import com.ghali.automation.constants.Constants;
import com.ghali.automation.factory.UtilityFactory;

public class AppiumUtils {
	private final static Logger LOG = Logger.getLogger(AppiumUtils.class);

	private int[] ports = new int[] { 4721, 4722, 4723, 4724, 4725, 4726, 4727, 4728, 4729, 4730 };
	private String hostName = Constants.GenericConstants.LOCALHOST_URL;
	private String nodePath = System.getenv("NODE");
	private String appiumMainJS = System.getenv("APPIUM_PATH");
	private Process process;
	private String appiumServerPort;
	private String appiumServerUrl;

	public AppiumUtils() {
	}

	/**
	 * Starts the appium server.
	 *
	 * @return {@link String}, socket at which appium server is running.
	 * @throws Exception
	 */
	public void startAppiumServer(String driverName) {

		LOG.info("Starting Appium Server");
		driverName = "android";
		if (driverName.equalsIgnoreCase(Platform.ANDROID.name()) || driverName.equalsIgnoreCase(Platform.IOS.name())) {

			String command = "";
			int availablePort = 0;

			if (this.appiumMainJS == null || this.nodePath == null) {
				try {
					throw new Exception("Error : Environment varaiable not found for appium_path or node");
				} catch (Exception e) {
					e.printStackTrace();
					Assert.fail(
							"Error : Please set the environment variables for appium_path or node or verify if these environmental variables are accessible via eclipse");
				}
			}

			for (int i = 0; i < this.ports.length; i++) {
				if (!UtilityFactory.getJavaUtils().isPortInUse(this.hostName, this.ports[i])) {
					availablePort = this.ports[i];
					this.appiumServerPort = String.valueOf(availablePort);
					LOG.debug("Available port : " + availablePort);
					break;
				}
			}
			if (availablePort == 0) {
				try {
					throw new Exception("Error : Ports are not available");
				} catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Error : Kill any port between mentioned in the ports integer array");
				}
			} else {

				LOG.debug("Command to start appium server \n" + command);
			}

			try {
				this.process = Runtime.getRuntime().exec(
						"cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4721 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			} catch (IOException e) {

				e.printStackTrace();
			}

			LOG.debug("Wait for appium server to start - ideally it should not take more than 10 seconds");
			while (!UtilityFactory.getJavaUtils().isPortInUse(hostName, availablePort)) {
				LOG.debug("Appium is not started. waiting 1 second.");
				UtilityFactory.getJavaUtils().sleep(1000);
			}

			this.appiumServerUrl = String.format("http://%s:%s/wd/hub", hostName, availablePort);

			LOG.info("Appium server started on : " + this.appiumServerUrl);
		}
	}

	/**
	 * Returns the appium server url.
	 *
	 * @return appiumServerUrl.
	 */
	public String getAppiumServerUrl() {

		if (this.appiumServerUrl != null) {
			return this.appiumServerUrl;
		} else {
			LOG.error("Error : appium server is not started");
			throw new NullPointerException("appiumServerUrl is Null, as appium server is not started.");
		}
	}

	/**
	 * Returns the port running the appium server.
	 *
	 * @return portNumber.
	 */
	public String getAppiumServerPort() {

		if (this.appiumServerPort != null) {
			return this.appiumServerPort;
		} else {
			LOG.error("Error : appium server is not started");
			throw new NullPointerException("appiumServerUrl is Null, as appium server is not started.");
		}
	}

}
