package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest	{
	
	@BeforeClass
	public void accPageSetUp() {
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void validateAccountsPageTitle() {
		Assert.assertEquals(accountspage.getAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void validateAccountsPageUrl() {
		Assert.assertTrue(accountspage.getAccountsPageURL());
	}
	
	@Test(priority=3)
	public void validateAccountsPageHeader() {
		Assert.assertEquals(accountspage.getAccountsPageHeader(), Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test(priority=4)
	public void validateAccountsPageSectionList() {
		Assert.assertEquals(accountspage.getAccountPageSectionList(), Constants.ACCOUNTS_PAGE_SECTION_LIST);
	}
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {{"iphone"},{"apple"},{"imac"}};
	}
	
	
	@Test(dataProvider="getProductName",priority=5)
	public void validateAccountsPageProductSearch(String productName) {
		searchpage = accountspage.doSearch(productName);
	}
	
	@DataProvider
	public Object[][] getSubproductName() {
		return new Object[][] {{"iphone","iPhone"},{"mac","MacBook Air"},{"apple","Apple Cinema 30\""}};
	}
	
	
	@Test(dataProvider="getSubproductName",priority=6)
	public void validateSelectSubProduct(String product, String subProduct) {
		searchpage=accountspage.doSearch(product);
		searchpage.validateSelectSubProduct(subProduct);
	}

}
