package com.ghali.automation.constants;

public final class Constants {

	private Constants() {
	}

	public static final class DriverNames {

		private DriverNames() {
		}

		public static final String ANDROID = "android";
		public static final String IOS = "ios";
		public static final String FIREFOX = "firefox";
		public static final String CHROME = "chrome";
	}

	public static final class AppiumConstants {

		private AppiumConstants() {
		}

		public static final String AUTOMATION_NAME_ANDROID = "uiautomator2";
		public static final String PLATFORM_VERSION = "platformVersion";
		public static final String AUTOMATION_NAME_UIAUTOMATOR2 = "uiautomator2";
		public static final String AUTOMATION_PLATFORM_ANDROID = "android";
	}

	public static final class GenericConstants {

		public static final String ROOT;
		public static final String HOME;

		static {
			ROOT = System.getProperty("user.dir");
			HOME = System.getProperty("user.home");
		}

		private GenericConstants() {

		}

		public static final String CONFIG_PROPERTIES = ROOT + "/Config.properties";
		public static final String PLATFORM_TYPE = "platformType";
		public static final String PLATFORM_VERSION = "platformVersion";
		public static final String DRIVER_NAME = "driverName";
		public static final String APK_PATH = "apkPath";
		public static final String LOCALHOST_URL = "127.0.0.1";

		public static final String UP_ARROW = "up";
		public static final String DOWN_ARROW = "down";
		public static final String LEFT_ARROW = "left";
		public static final String RIGHT_ARROW = "right";

	}

	public final class MessagesConstants {

		private MessagesConstants() {
		}

		public static final String WAS_NOT_LOADED = " was not loaded...";

	}

	public static final class CommandLineConstants {

		private CommandLineConstants() {
		}

		public static final String ADB_LOCATION = System.getenv("ANDROID_HOME") + "/platform-tools/adb ";
		public static final String ADB_DEVICES = ADB_LOCATION + "devices";

	}

	public static final class WeatherApis {

		private WeatherApis() {
		}

		public static final String ATTEMPTS = "attempts";

	}

	public static final class WeatherApiEndpoints {

		private WeatherApiEndpoints() {

		}

		private static final String DOMAIN = "api.openweathermap.org";
		private static final String VERSION = "2.5";
		public static final String BASE_URL = "https://" + DOMAIN + "/data/" + VERSION + "/weather?q=";
	}

//	/data/2.5/weather?q={city name}&appid={your api key}
}