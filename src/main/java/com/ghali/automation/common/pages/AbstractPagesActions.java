package com.ghali.automation.common.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ghali.automation.constants.Constants.GenericConstants;
import com.ghali.automation.factory.UtilityFactory;
import com.ghali.automation.interfaces.DriverInterface;

public class AbstractPagesActions {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractPagesActions.class);

	private DriverInterface driverInterface;
	private WebDriver driver;
	private WebDriverWait wait;

	public AbstractPagesActions(DriverInterface driverInterface) {

		this.driverInterface = driverInterface;
		this.driver = (WebDriver) this.driverInterface.getDriver();
		this.wait = new WebDriverWait(driver, 10);
	}

	/**
	 * @return {@link DriverInterface} which was initialized in the constructor.
	 */
	public DriverInterface getDriverInterface() {

		return this.driverInterface;
	}

	/**
	 * Navigates from current page to the url in the argument.
	 *
	 * @param url
	 */
	public void navigateTo(String url) {

		LOG.debug(String.format("Navigating to %s ", url));
		((WebDriver) this.driverInterface.getDriver()).get(url);
	}

	/**
	 * Refreshes the current page.
	 */
	public void reloadPage() {

		((WebDriver) this.driverInterface.getDriver()).navigate().refresh();
		UtilityFactory.getJavaUtils().sleep(3000);
	}

	/**
	 * Clicks on the element in the argument.
	 *
	 * @param element
	 */
	public void clickElement(WebElement element) {
		element.click();
	}

	/**
	 * Enters the keys in the argument by overriding the existing value in the
	 * element.
	 *
	 * @param element
	 * @param keysToSend
	 *
	 */
	public void enterText(WebElement element, String keysToSend) {
		// Wait for element to be visible and then perform click
		element.clear();
		element.sendKeys(keysToSend);
	}

	public void click_Text(String string) {
		WebElement webElement = driver.findElement(By.xpath(string));

		if (!(webElement.isSelected())) {
			driver.findElement(By.xpath(string)).click();
		}
	}

	/**
	 * Gets the text from the element.
	 *
	 * @param element
	 * @return textFromTheElement
	 */
	public String getText(WebElement element) {
		// Wait for element to be visible and then perform click
		return element.getText();
	}

	/**
	 * Gets the text attribute value of the element.
	 *
	 * @param element
	 * @param attribute
	 * @return textFromTheElement
	 */
	public String getAttribute(WebElement element, String attribute) {
		// Wait for element to be visible and then perform click
		return element.getAttribute(attribute);
	}

	/**
	 * Checks if an element is present in a page.
	 *
	 * @param element
	 * @return {@link Boolean} status if the element is displayed.
	 */
	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}


	/**
	 * Waits until element is visible in the UI.
	 *
	 * @param element
	 */
	public void waitForElementVisibility(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Waits until element is visible in the UI.
	 *
	 * @param element
	 * @param timeout
	 */
	public void waitForElementVisibility(WebElement element, long timeout) {

		new WebDriverWait((RemoteWebDriver) this.driverInterface.getDriver(), timeout)
				.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Waits for an element identified by a locator to be visible in UI
	 *
	 * @param locator
	 * @param timeout in milli seconds
	 */
	public void waitForElementVisibility(By locator, long timeout) {

		new WebDriverWait((WebDriver) this.driverInterface.getDriver(), timeout)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Waits until the element disappears on the UI
	 *
	 * @param element
	 */
	public void waitForElementInvisibility(WebElement element) {

		new WebDriverWait((WebDriver) this.driverInterface.getDriver(), 30)
				.until(ExpectedConditions.invisibilityOf(element));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Performs MouseHover action on a particular field.
	 *
	 * @param element
	 */
	public void performMouseHover(WebElement element) {

		Actions actions = new Actions((WebDriver) this.driverInterface.getDriver());
		actions.moveToElement(element).perform();
		actions.click();
	}

	/**
	 * Simulates TAB key.
	 */
	public void simulateTabKey() {
		Actions actions = new Actions((WebDriver) this.driverInterface.getDriver());
		actions.sendKeys(Keys.TAB).build().perform();
	}

	/**
	 * Simulates ENTER Key
	 */
	public void simulateEnterKey() {
		Actions actions = new Actions((WebDriver) this.driverInterface.getDriver());
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * Simulates BACK_SPACE Key
	 */
	public void simulateBackSpaceKey() {
		Actions actions = new Actions((WebDriver) this.driverInterface.getDriver());
		actions.sendKeys(Keys.BACK_SPACE).build().perform();
	}

	/**
	 * Simulates arrow keys
	 *
	 * @param arrow [String]
	 */
	public void simulateArrowKey(String arrow) {
		Actions actions = new Actions((WebDriver) this.driverInterface.getDriver());
		switch (arrow) {
		case GenericConstants.UP_ARROW:
			actions.sendKeys(Keys.ARROW_UP).build().perform();
			break;
		case GenericConstants.DOWN_ARROW:
			actions.sendKeys(Keys.ARROW_DOWN).build().perform();
			break;
		case GenericConstants.LEFT_ARROW:
			actions.sendKeys(Keys.ARROW_LEFT).build().perform();
			break;
		case GenericConstants.RIGHT_ARROW:
			actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
			break;
		}
	}

	/**
	 * Checks if element is enabled in the page.
	 *
	 * @param element {@link WebElement}
	 * @return {@link Boolean}
	 */
	public boolean isEnabled(WebElement element) {
		try {
			return element.isEnabled();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	/**
	 * Returns the handles for all the available browser tabs
	 *
	 * @return {@link ArrayList}
	 */
	public ArrayList<String> getTabs() {
		ArrayList<String> tabs = new ArrayList<String>(
				((WebDriver) this.driverInterface.getDriver()).getWindowHandles());
		return tabs;
	}

	/**
	 * Switches between open browser tabs
	 *
	 * @param tabs  {@link ArrayList}: List of open browser tabs
	 * @param index {@link int}: Index of browser tab to switch to. Starts from
	 *              zero.
	 */
	public void switchToBrowserTab(ArrayList<String> tabs, int index) {
		((WebDriver) this.driverInterface.getDriver()).switchTo().window(tabs.get(index));
	}

	/**
	 * Closes currently active tab
	 */
	public void closeTab() {
		((WebDriver) this.driverInterface.getDriver()).close();
	}

	/**
	 * Switch to an iFrame
	 *
	 * @param iFrame
	 */
	public void switchiFrame(WebElement iFrame) {
		((WebDriver) this.driverInterface.getDriver()).switchTo().frame(iFrame);
	}

	/**
	 * Clears the already existing content at an webelement such as a text box.
	 *
	 * @param element
	 */
	public void clear(WebElement element) {
		element.clear();
	}

	/**
	 * Send the value mentioned in the argument to the webelement
	 *
	 * @param element
	 * @param keysToSend
	 */
	public void sendKeys(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	/**
	 * Launches new blank tab
	 */
	public void openBlankTab() {

		((JavascriptExecutor) driver).executeScript("window.open()");
		UtilityFactory.getJavaUtils().sleep(2000);
	}

	/**
	 * Gives current page title
	 *
	 * @param element
	 */
	public String getPageTitle() {
		String pageTitle = driver.getTitle().trim();
		return pageTitle;
	}

	/**
	 * Scroll to an element
	 *
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		UtilityFactory.getJavaUtils().sleep(1000);
	}

	/**
	 * Returns the value selected in the Dropdown
	 *
	 * @param element
	 * @return string
	 */
	public String getSelectedDropdownValue(WebElement element) {
		Select select = new Select(element);
		String optionSelected = select.getFirstSelectedOption().getText().trim();
		return optionSelected;
	}

	/**
	 * Returns css value based the attribute passed
	 *
	 * @param element
	 * @param attribute
	 * @return string
	 */
	public String getCssValue(WebElement element, String attribute) {
		return element.getCssValue(attribute).trim();
	}

	/**
	 * Select by index from Dropdown
	 *
	 * @param dropdown
	 * @param index
	 */
	public void selectDropdownValueByIndex(WebElement dropdown, int index) {
		Select DropdownElement = new Select(dropdown);
		DropdownElement.selectByIndex(index);
	}

	/**
	 * Select by text from Dropdown
	 *
	 * @param dropdown
	 * @param text
	 */
	public void selectDropdownByVisibleText(WebElement dropdown, String text) {
		Select DropdownElement = new Select(dropdown);
		DropdownElement.selectByVisibleText(text);
	}

	/**
	 * Select Value from Dropdown
	 *
	 * @param dropdown
	 * @param value
	 */
	public void selectDropdownValue(WebElement dropdown, String value) {
		Select dropdownElement = new Select(dropdown);
		dropdownElement.selectByValue(value);
	}

	/**
	 * Retrieve all the Dropdown Options
	 *
	 * @param dropdown
	 * @return List of options
	 */
	public List<WebElement> getDropdownOptions(WebElement dropdown) {
		Select dropdownElement = new Select(dropdown);
		List<WebElement> options = dropdownElement.getOptions();
		return options;
	}

	/**
	 * Navigates backwards
	 */
	public void navigateToPreviousPage() {
		((WebDriver) this.driverInterface.getDriver()).navigate().back();
	}

	/**
	 * Switches to new Frame having frameId
	 *
	 * @param frameId
	 */
	public void switchToFrame(String frameId) {
		((WebDriver) this.driverInterface.getDriver()).switchTo().frame(frameId);
	}

	/**
	 * Switches back from frame to default window
	 */
	public void switchToDefaultContent() {
		((WebDriver) this.driverInterface.getDriver()).switchTo().defaultContent();
	}

	/**
	 * Clicks on the element using JS executor
	 *
	 * @param element
	 */
	public void clickElementUsingJs(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) (WebDriver) this.driverInterface.getDriver();
		jse.executeScript("arguments[0].click();", element);
	}

	/**
	 * Waits until text to be present in an element.
	 *
	 * @param element
	 * @param text
	 */
	public void waitForElementTextVisibility(WebElement element, String text) {

		new WebDriverWait((WebDriver) this.driverInterface.getDriver(), 30)
				.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * Wait till element is visible for timeInMillis milliseconds
	 *
	 * @param element
	 * @param timeInMillis
	 */
	public void waitForElementVisibility(WebElement element, int timeInMillis) {

		new WebDriverWait((WebDriver) this.driverInterface.getDriver(), (timeInMillis / 1000))
				.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Waits until element is clickable in the UI.
	 *
	 * @param element
	 */
	public void waitForElementClickable(WebElement element) {

		new WebDriverWait((WebDriver) this.driverInterface.getDriver(), 30)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait for the element to be clickable. 5 seconds is the max timeout.
	 *
	 * @param element
	 */
	public void clickElementWhenClickable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		this.clickElement(element);
	}

	/**
	 * Get current page url
	 *
	 * @return String
	 */
	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Wait till the text is displayed on the page. This can be used to locate the
	 * text anywhere on the page.
	 *
	 * @param text {@link String} Text to look for.
	 */
	public void waitForTextVisibility(String text) {
		new WebDriverWait((WebDriver) this.driverInterface.getDriver(), 30)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + text + "')]")));
	}

	/**
	 * Get text from alert popup message
	 *
	 * @return {@link String} Message from popup UI
	 */
	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	/**
	 * Accepts alert popup.
	 */
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	/**
	 * Dismiss alert popup.
	 */
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

}
