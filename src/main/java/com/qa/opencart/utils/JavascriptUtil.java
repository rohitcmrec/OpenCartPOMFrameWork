package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {
	
	private WebDriver driver;
	
	public JavascriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public void doClickByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public void doSendKeysByJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value=arguments[1]", element, value);
	}
	
	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("return document.title").toString();
	}
	
	public String getInnerTextByJS() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript(" return document.documentElement.innerText").toString();
	}
	
	public void refreshByJS() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(0)");
	}
	
	public void alertByJS(String alert) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("alert('"+alert+"')");
	}
	
	public void drawBorderByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red'",element);
	}
	
	public void changeColorByJS(WebElement element, String color) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'",element);
	}
	
	public void flashByJS(WebElement element, String color) {
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		String elecolor = element.getCssValue("backgroundColor");
		for(int i=0;i<10;i++) {
			changeColorByJS(element, elecolor);
			changeColorByJS(element, color);
		}
	}
	
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
	}
	
	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void scrollHeight(String height) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+height+")");
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);
	}

}
