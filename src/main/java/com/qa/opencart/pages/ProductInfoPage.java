package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	private Map<String, String> hmap;
	
	private By img = By.cssSelector("div#content img");
	private By productDetails = By.xpath("(//div[@id='content']//ul)[3]/li");
	private By productPrice = By.xpath("(//div[@id='content']//ul)[4]/li");
	
	private By quantity = By.cssSelector("input#input-quantity");
	private By addToCart = By.cssSelector("button#button-cart");
//	private By cartMsg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private By cartMsg = By.xpath("//div[@class='alert alert-success alert-dismissible']");

	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementutil = new ElementUtil(this.driver);
	}
	
	public int validateProductImage() {
		return elementutil.getElements(img).size();
	}
	
	public String validateaddToCart() throws InterruptedException {
		elementutil.doSendkeys(quantity, "2");
		elementutil.doClick(addToCart);
		Thread.sleep(5000);
		String msg = elementutil.waitByVisibilityOfEement(cartMsg, Constants.DEFAULT_TIME_OUT).getText();
		System.out.println(msg);
		return msg;
		
	}
	
	public Map<String, String> getProductInfo() {
		hmap = new HashMap<String, String>();
		validateProductDetails();
		validateProductPrice();
		return hmap;
	}
	
	private void validateProductDetails() {
		List<WebElement> Details = elementutil.getElements(productDetails);
		for(WebElement e:Details) {
			String[] detailsText = e.getText().split(":");
			String detailKey=detailsText[0].trim();
			String detailValue=detailsText[1].trim();
			hmap.put(detailKey, detailValue);
			
		}
	}
	
	private void validateProductPrice() {
		List<WebElement> Price = elementutil.getElements(productPrice);
		hmap.put("Price",Price.get(0).getText());
		String tax = Price.get(1).getText().split(":")[1];
		hmap.put("ETax", tax.trim());
			
		
	}

}
