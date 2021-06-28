package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetUp() {
		registrationpage = loginpage.naviagteToResgister();
	}
	
	public String mailGenerator() {
		Random random = new Random();
		String email = "testautomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getData() {
		Object data [][] = ExcelUtil.getExcelData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider="getData")
	public void userRegistration(String firstName, String lastName, String phone, String password, String subscribe) {
		Assert.assertTrue(registrationpage.doRegister(firstName, lastName, mailGenerator(), phone, password, subscribe));
	}

}
