package com.ghali.automation.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ghali.automation.common.pages.AbstractPagesActions;
import com.ghali.automation.factory.UtilityFactory;
import com.ghali.automation.interfaces.DriverInterface;

public class NdtvWeatherPage extends AbstractPagesActions {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractPagesActions.class);
	private static final String USER_XPATH = "//*[@id=\'%s\']";
	private static final String CITY_SELECTOR_MAP = "//*[@id=\"map_canvas\"]//div[contains(@class, 'cityText') and text()='%s']";

		public NdtvWeatherPage(DriverInterface driverInterface) {
		super(driverInterface);
		org.openqa.selenium.support.PageFactory.initElements((WebDriver) driverInterface.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='searchBox']")
	private WebElement SearchCity;

	@FindBy(xpath = "//*[@id=\'map_canvas\']")
	WebElement mapCanvas;

	@FindBy(xpath = "//*[@id='map_canvas']//div[contains(@class,'leaflet-popup-content-wrapper')]/div/div/span[contains(@class,'heading')]/following-sibling::span/following-sibling::span/following-sibling::span/b")
	WebElement tempDeg;

	@FindBy(xpath = "//*[@id='map_canvas']//div[contains(@class,'leaflet-popup-content-wrapper')]/div/div/span[contains(@class,'heading')]/following-sibling::span/following-sibling::span/following-sibling::span/following-sibling::span/b")
	WebElement tempFah;

	public float getTempreture(String cityName) {
			String fullXpath = String.format(USER_XPATH, cityName);
			String path = String.format(CITY_SELECTOR_MAP, cityName);
			String tempString;
			// Search city
			UtilityFactory.getJavaUtils().sleep(100);
			try {
				enterText(SearchCity, cityName);
				simulateEnterKey();
				UtilityFactory.getJavaUtils().sleep(100);
				click_Text(fullXpath);
				click_Text(path);
			} catch (Exception e) {
				LOG.error("City is not found on the Map");
			}

			tempString = tempDeg.getText();
			String[] str = tempString.split(":");
			return (Float.parseFloat(str[1].trim()));

		}

}



