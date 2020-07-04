package com.ghali.automation.factory;

import com.ghali.automation.utils.AndroidUtils;
import com.ghali.automation.utils.AppiumUtils;
import com.ghali.automation.utils.JavaUtils;
import com.ghali.automation.utils.RestApiUtils;

public class UtilityFactory {

	private static JavaUtils javaUtils;
	private static AppiumUtils appiumUtils;
	private static AndroidUtils androidUtils;
	private static RestApiUtils restApiUtils;

	public static JavaUtils getJavaUtils() {

		if (javaUtils == null) {
			javaUtils = new JavaUtils();
		}
		return javaUtils;
	}

	public static AppiumUtils getAppiumUtils() {

		if (appiumUtils == null) {
			appiumUtils = new AppiumUtils();
		}
		return appiumUtils;
	}

	public static AndroidUtils getAndroidUtils() {

		if (androidUtils == null) {
			androidUtils = new AndroidUtils();
		}
		return androidUtils;
	}

	public static RestApiUtils getRestApiUtils() {

		if (restApiUtils == null) {
			restApiUtils = new RestApiUtils();
		}
		return restApiUtils;
	}
}
