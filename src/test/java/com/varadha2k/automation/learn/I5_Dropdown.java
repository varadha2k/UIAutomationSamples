package com.varadha2k.automation.learn;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class I5_Dropdown {
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void dropdownTest() {
		driver.get("http://the-internet.herokuapp.com/dropdown");
		WebElement dropdownList = driver.findElement(By.id("dropdown"));
		List<WebElement> options = dropdownList.findElements(By.tagName("option"));
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals("Option 1")) {
				options.get(i).click();
			}
		}
		String selectedOption = "";
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).isSelected()) {
				selectedOption = options.get(i).getText();
			}
		}

		assertThat(selectedOption, is("Option 1"));
	}

	@Test
	public void dropdownTestRedux() {
		driver.get("http://the-internet.herokuapp.com/dropdown");
		Select selectList = new Select(driver.findElement(By.id("dropdown")));
		selectList.selectByVisibleText("Option 1");
		// You could also use select.selectByValue("1");
		assertThat(selectList.getFirstSelectedOption().getText(), is(equalTo("Option 1")));
	}

}