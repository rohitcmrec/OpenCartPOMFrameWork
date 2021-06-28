package com.qa.opencart.factory;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionmanager;
	public static ThreadLocal<WebDriver> threadlocal = new ThreadLocal<WebDriver>();
	

	/**
	 * This method will return the driver
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {

		optionmanager = new OptionsManager(prop);
		highlight=prop.getProperty("highlight");
		String browser = prop.getProperty("browser").trim();
		System.out.println("The borwser name is " + browser);

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			}else {
			threadlocal.set(new ChromeDriver(optionmanager.getChromeOptions()));
			}
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			}else {
				threadlocal.set(new FirefoxDriver(optionmanager.getFirefoxOptions()));
			}
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Please pass the right browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	private void init_remoteDriver(String browser) {
		System.out.println("running on the browser :"+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionmanager.getChromeOptions());
			try {
				threadlocal.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if(browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionmanager.getFirefoxOptions());
			try {
				threadlocal.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized WebDriver getDriver() {
		return threadlocal.get();
	}
	/**
	 * This method is used to initilaize the properties file
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");
		if(env==null) {
			System.out.println("Running on environment: PRD");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}
		else {
			System.out.println("Running on environment: "+env);
			try {
				switch (env) {
				case "stg":
					ip = new FileInputStream("./src/test/resources/config/stg.config.properties");
					break;
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				default:
					break;
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot() {
		TakesScreenshot ts = (TakesScreenshot)getDriver();
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return path;
		
	}

}
