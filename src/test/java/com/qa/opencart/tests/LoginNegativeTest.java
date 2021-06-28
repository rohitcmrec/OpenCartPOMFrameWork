package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class LoginNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] getLoginData() {
		Object [][] loginData = ExcelUtil.getExcelData(Constants.LOGIN_SHEET_NAME);
		return loginData;
	}

	@Test(dataProvider="getLoginData")
	public void doLoginApp(String username, String passwrod) {
		Assert.assertTrue(loginpage.doAppLogin(username, passwrod));
	}

}
