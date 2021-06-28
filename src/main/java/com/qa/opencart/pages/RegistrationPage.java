package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil elementutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By phone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline']/input)[1]");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline']/input)[2]");
	private By agree = By.name("agree");

	private By login = By.xpath("//input[@type='submit']");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");
	private By msg = By.cssSelector("div#content h1");

	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		elementutil = new ElementUtil(driver);
	}

	public boolean doRegister(String firstName, String lastName, String email,String phone, String password, String subscribe) {
		elementutil.doSendkeys(this.firstName, firstName);
		elementutil.doSendkeys(this.lastName, lastName);
		elementutil.doSendkeys(this.email, email);
		elementutil.doSendkeys(this.phone, phone);
		elementutil.doSendkeys(this.password, password);
		elementutil.doSendkeys(confirmPassword, password);
		if(subscribe.equalsIgnoreCase("yes")) {
			elementutil.doClick(subscribeYes);
		}else
			elementutil.doClick(subscribeNo);

		elementutil.doClick(agree);
		elementutil.doClick(login);
		
		String text  =elementutil.waitByVisibilityOfEement(msg, Constants.DEFAULT_TIME_OUT).getText();

		if(text.equalsIgnoreCase(Constants.REGISTER_SUCCESS_MESSG)) {
			elementutil.doClick(logout);
			elementutil.doClick(register);	
			return true;
		}else
			return false;
	}

}
