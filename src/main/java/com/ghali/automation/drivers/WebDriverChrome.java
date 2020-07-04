package com.ghali.automation.drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ghali.automation.interfaces.DriverInterface;

public class WebDriverChrome implements DriverInterface {
	private static final Logger LOG = LoggerFactory.getLogger(WebDriverChrome.class);
	private ChromeDriver driver;

	public WebDriverChrome() {
		LOG.info("Initiating Chrome drive launch");
		this.driver = new ChromeDriver();
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
