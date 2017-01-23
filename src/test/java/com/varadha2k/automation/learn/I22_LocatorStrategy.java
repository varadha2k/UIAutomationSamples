package com.varadha2k.automation.learn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by andrew on 8/22/15.
 */
public class I22_LocatorStrategy {
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
		driver = new ChromeDriver();
	}

	@Test
	public void locatorStategyCSS() {
		driver.get("http://the-internet.herokuapp.com/download");
		String link = driver.findElement(By.cssSelector("a")).getAttribute("href");
		System.out.println("1: " + link);
	}

	@Test
	public void locatorStategyPreciseCSS() {
		driver.get("http://the-internet.herokuapp.com/download");
		String link = driver.findElement(By.cssSelector("#content a")).getAttribute("href");
		System.out.println("2: " + link);
	}

	@Test
	public void locatorStategyExactCSS() {
		driver.get("http://the-internet.herokuapp.com/download");
		String link = driver.findElement(By.cssSelector("#content .example a")).getAttribute("href");
		System.out.println("3: " + link);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
