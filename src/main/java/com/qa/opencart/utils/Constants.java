package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String LOGIN_ERROR_MSG = "No match for E-Mail Address and/or Password";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int LOGIN_PAGE_LINKS_COUNT = 13;
	public static final List<String> LOGIN_PAGE_HEADER_LINKS = Arrays.asList("123456789","My Account",
																			"Wish List (0)","Shopping Cart","Checkout");
	
	public static final String ACCOUNTS_PAGE_TITLE ="My Account";
	public static final String ACCOUNTS_PAGE_HEADER ="Your Store";
	public static final List<String> ACCOUNTS_PAGE_SECTION_LIST = Arrays.asList("My Account","My Orders", "My Affiliate Account"
																			,"Newsletter");
	
	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	//****************sheetName********************************
	
	public static final String REGISTER_SHEET_NAME ="register";
	public static final String LOGIN_SHEET_NAME ="login";
}
