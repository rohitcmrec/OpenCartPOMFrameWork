package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetUp() {
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void getProductImage() {
		searchpage = accountspage.doSearch("mac");
		productinfopage = searchpage.validateSelectSubProduct("MacBook");
		Assert.assertTrue(productinfopage.validateProductImage()>0);
		
	}
	
	@Test
	public void addToCartMsg() throws InterruptedException {
		searchpage = accountspage.doSearch("mac");
		productinfopage = searchpage.validateSelectSubProduct("MacBook");
		Assert.assertTrue(productinfopage.validateaddToCart().contains("added"));
	}
	
	@Test
	public void validateProductInfo() {
		searchpage = accountspage.doSearch("mac");
		productinfopage = searchpage.validateSelectSubProduct("MacBook");
		Map<String,String>productInfoMap = productinfopage.getProductInfo();
		softassert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softassert.assertEquals(productInfoMap.get("ETax"), "$500.00");
		softassert.assertAll();
	}

}
