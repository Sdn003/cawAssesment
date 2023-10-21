package com.caw.commonFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitilaizeTest{
	
	public WebDriver driver = null;
	Properties properties = null;
	public Logger log = Logger.getLogger(InitilaizeTest.class);
	
	
	public Properties loadPropertyFile() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("config.properties");
		
		properties = new Properties();
		properties.load(fileInputStream);
		
		return properties;
	}
	
	@BeforeSuite
	public void launchBrowser() throws IOException {
		PropertyConfigurator.configure("logs.properties");
		log.info("Loading the Config properties");
		loadPropertyFile();
		
		String browser = properties.getProperty("browser");
		String driverLocation = properties.getProperty("driverLocation");
		String url = properties.getProperty("url");
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",driverLocation );
			log.info("Launching Chrome Browser");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver",driverLocation );
			log.info("Launching Firefox Browser");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		log.info("Launching the application");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
