package com.varadha2k.automation.learn;

// Chrome import statements
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import java.util.HashMap;
//import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class I2_Download {
	WebDriver driver;
	File folder;

	@Before
	public void setUp() throws Exception {
		folder = new File(UUID.randomUUID().toString());
		folder.mkdir();

		// Firefox
		/*
		 * FirefoxProfile profile = new FirefoxProfile();
		 * profile.setPreference("browser.download.dir",
		 * folder.getAbsolutePath());
		 * profile.setPreference("browser.download.folderList", 2);
		 * profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
		 * "image/jpeg, application/pdf, application/octet-stream");
		 * profile.setPreference("pdfjs.disabled", true); driver = new
		 * FirefoxDriver(profile);
		 */

		// Chrome
		System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", folder.getAbsolutePath());
		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		for (File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
	}

	@Test
	public void download() throws Exception {
		driver.get("http://the-internet.herokuapp.com/download");

		List<WebElement> elements = driver.findElements(By.cssSelector(".example a"));
		for (WebElement element : elements) {
			element.click();
			Thread.sleep(2000);
		}

		// Wait 2 seconds to download file
		File[] listOfFiles = folder.listFiles();

		// Make sure the directory is not empty
		assertThat(listOfFiles.length, is(not(0)));
		for (File file : listOfFiles) {
			// Make sure the downloaded file(s) is(are) not empty
			assertThat(file.length(), is(not((long) 0)));
		}
	}

}