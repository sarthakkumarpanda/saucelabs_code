package com.saucelabs.TestCases;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC1 {
	public RemoteWebDriver driver;
	public ChromeOptions options;
	public static final String SAUCE_USERNAME = "oauth-automationhubsarthak-ab1cc";
    public static final String SAUCE_ACCESS_KEY = "5323f316-51d3-45aa-8e12-f5a1e017590b";
	
    
    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {
    	MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.setCapability("access_key", System.getenv("SAUCE_ACCESS_KEY"));
        sauceOptions.setCapability("name", method.getName());
        sauceOptions.setCapability("browserVersion", "latest");
        options = new ChromeOptions();
        options.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url, options); 	
    }
    
    @Test
    public void verifyLogin() {
    	driver.get("https://tutorialsninja.com/demo");
    	driver.findElement(By.linkText("My Account")).click();
    	driver.findElement(By.linkText("Login")).click();
    	driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
    	driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
    	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    	Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
    }

}
