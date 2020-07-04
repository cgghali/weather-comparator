package com.ghali.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ghali.automation.common.pages.AbstractPagesActions;
import com.ghali.automation.interfaces.DriverInterface;

public class NdtvHomePage extends AbstractPagesActions {

	public NdtvHomePage(DriverInterface driverInterface) {
		super(driverInterface);
		org.openqa.selenium.support.PageFactory.initElements((WebDriver) driverInterface.getDriver(), this);

	}

	@FindBy(xpath = "//*[@id=\'h_sub_menu\']")
	private WebElement more;

	@FindBy(xpath = "//*[contains(text(),'WEATHER')]")
	private WebElement weather;

	public void ClickonMore() {

		clickElement(more);
	}

	public void ClickoNWeather() {

		clickElement(more);
		clickElement(weather);
	}


	public void navigateToHomePage(String url) {

		navigateTo(url);
	}

}
