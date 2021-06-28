package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC of the Open Cart - 001")
@Story("Design the Login page for Open Cart- US101")
public class LoginPageTest extends BaseTest{
	
	@Description("This method is to validate the current title")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void validateLoginPageTitleTest() {
		String title = loginpage.getTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("This method is to validate the forgot password link")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void validateForgotPwdLinkTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Description("This method is to validate the login functionality")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void validateLoginTest() {
		loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	
	@Description("This method is to validate the footer links")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void validateFooterLinksTest() {
		List<String>footerlinks = loginpage.getFooterLinks();
		Assert.assertTrue(footerlinks.contains("About Us"));
	}
	
	@Description("This method is to validate the shortcut links")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void validateShortCutLinks() {
		Assert.assertEquals(loginpage.getLinks().size(),Constants.LOGIN_PAGE_LINKS_COUNT);
		Assert.assertTrue(loginpage.getLinks().contains("Recurring payments"));
	}
	
	@Description("This method is to validate the header links")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void validateHeaderLinks() {
		System.out.println(loginpage.getHeaderLinks());
		Assert.assertEquals(loginpage.getHeaderLinks(), Constants.LOGIN_PAGE_HEADER_LINKS);
	}
}
