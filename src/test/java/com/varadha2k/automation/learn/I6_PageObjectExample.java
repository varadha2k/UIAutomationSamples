package com.varadha2k.automation.learn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by andrew on 8/22/15.
 */

public class I6_PageObjectExample {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
		driver = new ChromeDriver();
	}

	@Test
	public void workWithBasicAuthTest() {
		GoogleSearch google = new GoogleSearch(driver);
		google.searchFor("elemental selenium tips");
		boolean result = google.searchResultPresent("Recieve a Free, Weekly tip");
		assert (result == true);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

class GoogleSearch {

	private final String BASE_URL = "http://www.google.com";
	WebDriver driver;

	By searchBox = By.name("q");
	By searchBoxSubmit = By.name("btnK");
	By topSearchResult = By.id("gs_htif0");

	public GoogleSearch(WebDriver _driver) {
		this.driver = _driver;
		visit();
		//assert (verifyPage() == true);
	}

	public void visit() {
		driver.get(this.BASE_URL);
	}

	public void searchFor(String searchTerm) {
		driver.findElement(searchBox).clear();
		driver.findElement(searchBox).sendKeys(searchTerm);
		
		//Wait for 3 seconds for the result page to load
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.findElement(searchBoxSubmit).click();
	}

	public boolean searchResultPresent(String searchResult) {
		waitFor(topSearchResult);
		return driver.findElement(topSearchResult).getText().contains(searchResult);
	}

	public void waitFor(By locator) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public boolean verifyPage() {
		return driver.getCurrentUrl().contains("Google");
	}
}
