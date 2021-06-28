package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	// by locators
	
	private By logo = By.cssSelector("div#logo");
	private By sectionList = By.cssSelector("div#content h2");
	private By searchbox = By.name("search");
	private By searchButton = By.cssSelector("span.input-group-btn");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(this.driver);
	}
	
	public String getAccountsPageTitle() {
		return  elementutil.getPageTitle(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public boolean getAccountsPageURL() {
		return elementutil.waitForUrls(Constants.DEFAULT_TIME_OUT, "route=account");
	}
	
	public String getAccountsPageHeader() {
		return elementutil.getText(logo);
	}
	
	public List<String> getAccountPageSectionList() {
		return elementutil.getElementsTextList(sectionList);
	}
	
	public SearchResultsPage doSearch(String productName) {
		elementutil.doSendkeys(searchbox, productName);
		elementutil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}
