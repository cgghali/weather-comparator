package com.ghali.automation.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ghali.automation.constants.Constants;
import com.ghali.automation.factory.UtilityFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidUtils {

	private final static Logger LOG = Logger.getLogger(AndroidUtils.class);
	private AndroidDriver<?> androidDriver = null;

	public AndroidDriver<?> startAndroidApp() throws MalformedURLException {
		String connectedDevices = getConnectedDevices();

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				Constants.AppiumConstants.AUTOMATION_NAME_UIAUTOMATOR2);
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				UtilityFactory.getJavaUtils().getConfigMap().get(Constants.GenericConstants.DRIVER_NAME));
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, connectedDevices);
		desiredCapabilities.setCapability(MobileCapabilityType.APP,
				new File(UtilityFactory.getJavaUtils().getConfigMap().get(Constants.GenericConstants.APK_PATH))
						.getAbsolutePath());
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		desiredCapabilities.setCapability("autoGrantPermissions", Boolean.TRUE);
		desiredCapabilities.setCapability("session-override", Boolean.TRUE);

		this.androidDriver = new AndroidDriver<AndroidElement>(
				new URL(UtilityFactory.getAppiumUtils().getAppiumServerUrl()), desiredCapabilities);
		return androidDriver;
	}

	private String getConnectedDevices() {

		String deviceName = null;
		boolean found = false;
		Scanner input = new Scanner(
				UtilityFactory.getJavaUtils().executeCommand(Constants.CommandLineConstants.ADB_DEVICES));
		input.nextLine();
		while (input.hasNextLine()) {
			found = true;
			deviceName = input.nextLine().replace("device", "").trim();
		}

		if (!found) {
			LOG.error("ERROR : NO ANDROID DEVICES CONNECTED");
		}
		input.close();

		return deviceName;

	}

}
