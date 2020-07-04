package com.ghali.automation.drivers;

import java.net.MalformedURLException;

import com.ghali.automation.interfaces.DriverInterface;

import io.appium.java_client.android.AndroidDriver;

public class MobileAndroidDriver implements DriverInterface {

	private AndroidDriver<?> driver;

	public MobileAndroidDriver() throws MalformedURLException {
		// this.driver = UtilityFactory.getAndroidUtils().startAndroidApp();
	}

	@Override
	public Object getDriver() {

		return this.driver;
	}

	@Override
	public void closeDriver() {

		this.driver.close();
	}

}
