package com.varadha2k.automation.learn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class I13_WorkWithBasicAuth2 {
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
		driver = new ChromeDriver();
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void workWithBasicAuthTest2() {
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		String pageMessage = driver.findElement(By.cssSelector("p")).getText();
		assertThat(pageMessage, containsString("Congratulations!"));
	}

}