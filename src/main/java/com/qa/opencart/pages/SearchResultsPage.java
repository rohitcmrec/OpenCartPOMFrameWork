package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	private By prouductHeader = By.cssSelector("div#content h1");
	private By productList = By.cssSelector("div.caption a");
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		elementutil = new ElementUtil(this.driver);
	}
	
	public String validateProductHeader() {
		return elementutil.getText(prouductHeader);
	}
	
	public int validateProductListSize() {
		return elementutil.getElementsTextList(productList).size();
	}
	
	public ProductInfoPage validateSelectSubProduct(String subProductName) {
		List<WebElement>searchProductList = elementutil.waitForElementsToBeLocated(productList, Constants.DEFAULT_TIME_OUT);
		for(WebElement e:searchProductList) {
			String productName = e.getText();
			if(productName.equalsIgnoreCase(subProductName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
		
	}

}
