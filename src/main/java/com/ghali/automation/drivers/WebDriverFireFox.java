package com.ghali.automation.drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ghali.automation.interfaces.DriverInterface;


public class WebDriverFireFox implements DriverInterface {
	private static final Logger LOG = LoggerFactory.getLogger(WebDriverFireFox.class);
	private FirefoxDriver driver;

	public void WebDriverFirefox()
	{
		LOG.info("Initiating Firefox drive launch");
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		this.driver.manage().window().maximize();
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