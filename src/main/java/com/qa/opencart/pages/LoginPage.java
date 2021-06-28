package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	//By locators
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButon = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By footer = By.xpath("//div[@class='row']//div//li//a");
	private By shortcutlink = By.xpath("//div[@class='list-group']//a");
	private By headerlinks = By.xpath("//ul[@class='list-inline']//li");
	
	private By resgiter = By.linkText("Register");
	
	private By errorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//init the constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementutil = new ElementUtil(this.driver);
	}
	
	//actions/methods to perform the actions
	@Step("this method is fetching the title")
	public String getTitle() {
		return elementutil.getPageTitle(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Step("this method id fetching the forgot password link")
	public boolean isForgotPwdLinkExist() {
		return elementutil.doIsDiplayed(forgotPwdLink);
	}
	
	@Step("this method is trying to login into the app with username:{0} and password:{1}")
	public AccountsPage doLogin(String un, String pwd) {
		elementutil.waitByVisibilityOfEement(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		elementutil.doSendkeys(password, pwd);
		elementutil.doClick(loginButon);
		return new AccountsPage(driver);
	}
	
	@Step("this method is trying to login into the app")
	public boolean doAppLogin(String un, String pwd) {
		elementutil.waitByVisibilityOfEement(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);;
		elementutil.doSendkeys(password, pwd);
		elementutil.doClick(loginButon);
		String error_msg = elementutil.waitByVisibilityOfEement(errorMsg, Constants.DEFAULT_TIME_OUT).getText();
		if(error_msg.contains(Constants.LOGIN_ERROR_MSG))
			return true;
		else
			return false;
	}
	@Step("this method is trying to fetch the footer links")
	public List<String> getFooterLinks() {
		return elementutil.getElementsTextList(footer);
	}
	
	@Step("this method is trying to fetch the shotcut links")
	public List<String> getLinks() {
		return elementutil.getElementsTextList(shortcutlink);
	}
	
	@Step("this method is trying to fetch the header links")
	public List<String> getHeaderLinks() {
		return elementutil.getElementsTextList(headerlinks);
	}
	
	public RegistrationPage naviagteToResgister() {
		elementutil.doClick(resgiter);
		return new RegistrationPage(driver);
	}

}
