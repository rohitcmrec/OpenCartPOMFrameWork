package com.qa.opencart.utils;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavascriptUtil jutil;

	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		jutil = new JavascriptUtil(this.driver);
	}

	public WebElement getElement(By locator)
	{
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jutil.flashByJS(ele, "rgb(0,200,0)");
		}
		return ele;
	}

	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}

	public void doSendkeys(By locator,String value)
	{
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public boolean doIsDiplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public String getText(By locator)
	{
		return getElement(locator).getText();
	}


	public List<String> getElementsTextList(By locator)
	{
		List<WebElement> linkslist = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for(WebElement e:linkslist)
		{
			String text = e.getText();
			if(!text.isEmpty())
			{
				eleTextList.add(text);
			}
		}

		return eleTextList;
	}

	public void getElementsText(By locator)
	{
		getElementsTextList(locator).stream().forEach(e->System.out.println(e));
	}

	public List<String> getAttributeValue(By locator, String atr)
	{
		List<WebElement> atrlist = getElements(locator);
		List<String> elelist = new ArrayList<String>();

		for(WebElement e:atrlist)
		{
			String atrvalue = e.getAttribute(atr);
			if(atrvalue!=null && !atrvalue.isEmpty())
			{
				elelist.add(atrvalue);
			}
		}
		return elelist;
	}
	/****************************************Select Based Drop Down ***************************
	 * @param locator
	 * @param value
	 */

	public void doSelectDropDownbyVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectDropDownbyValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownbyIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}


	public List<String> doGetOptionListText(By locator) {
		Select select = new Select(getElement(locator));
		List<String> optionTextList = new ArrayList<String>();
		List<WebElement>optionList = select.getOptions();

		for(WebElement e:optionList) {
			optionTextList.add(e.getText());
		}
		return optionTextList;
	}

	public void printDropDownoptions(By locator) {
		doGetOptionListText(locator).stream().forEach(e->System.out.println(e));
	}

	public void doSelectValueFromDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement>optionList = select.getOptions();

		for(WebElement e:optionList) {
			if(e.getText().equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}
	}

	public void doSelectValues(By locator, String value) {
		List<WebElement> optionList = getElements(locator);

		for(WebElement e:optionList) {
			if(e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	/***************************************************************************************
	 * The below method is for selecting an option from drop-down
	 * without using Select tag
	 */

	public void doSelectChoiceByAttributeType(By locator, String atrType, String... value) {
		List<WebElement> optionList = driver.findElements(locator);
		if(!value[0].equalsIgnoreCase("All")) {

			for(int i=0;i<optionList.size();i++) {

				for(int j=0;j<value.length;j++) {

					if(optionList.get(i).getAttribute(atrType).equals(value[j])) {

						optionList.get(i).click();
					}
				}
			}
		}
		else {
			for(WebElement e:optionList) {
				e.click();
			}
		}
	}
	
	/************************************************Actions Class***************************************************
	 * 
	 */
	
	public void doMoveToElement(WebDriver driver,By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).perform();
		
	}
	
	public void doMoveToElement(WebDriver driver,By locator_parent, By locator_child ) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator_parent)).perform();
		Thread.sleep(3000);
		action.moveToElement(getElement(locator_child)).perform();
	}
	
	public void doDragDrop(WebDriver driver, By locator_src, By locator_trg){
		Actions action = new Actions(driver);
		action.clickAndHold(getElement(locator_src)).moveToElement(getElement(locator_trg)).release().build().perform();
	}
	
	public void doActionSend(WebDriver driver, By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionClick(WebDriver driver, By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
	}
	
	/*************************************************wait**********************************************************/
	
	/***
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingTime - The duration in milliseconds to sleep between polls.
	 * @return
	 */
	
	
	public WebElement waitByPresenceOfElementLocated(By locator, int timeout, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, pollingTime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/******
	 * An expectation for checking that an element is present on the DOM of a page. 
	 * This does notnecessarily mean that the element is visible
	 * @param locator
	 * @param driver
	 * @param timeout
	 * @return WebElement */
	
	public WebElement waitByPresenceOfElementLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**************
	 * An expectation for checking that an element, known to be present on the DOM of a page, isvisible. 
	 * Visibility means that the element is not only displayed but also has a height andwidth that is greater than 0.
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return WebElement */
	
	public WebElement waitByVisibilityOfEement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	
	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(int timeout) {
		waitForAlert(timeout).accept();
	}
	
	public void dismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}
	
	public String getAlertText(int timeout) {
		return waitForAlert(timeout).getText();
	}
	
	/***
	 * An expectation for the URL of the current page to contain specific text.
	 * @param timeout
	 * @param url_fraction
	 * @return
	 */
	public boolean waitForUrls(int timeout, String url_fraction) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.urlContains(url_fraction));
	}
	
	
	public boolean waitForUrlToBe(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlToBe(url));
		
	}
	
	public String getPageTitle(int timeout, String title) {
		waitForTitleContains(timeout, title);
		return driver.getTitle();
	}
	
	public boolean waitForTitleContains(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	
	public boolean waitForTitleIs(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.titleIs(title));
	}
	
	public void waitForFrameToBeVisible(String idORname, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idORname));
	}
	
	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	/***
	 * An expectation for checking that all elements present on the web page that match the locatorare visible. 
	 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsToBeLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void getElementsText(By locator, int timeout) {
		waitForElementsToBeLocated(locator, timeout).stream().forEach(e->System.out.println(e.getText()));
	}
	
	/********
	 * 
	 * @param locator
	 * @param timeout
	 * @param polling
	 */
	public WebElement waitForPresenceOfElementFluentWait(By locator, int timeout, int polling) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(StaleElementReferenceException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
