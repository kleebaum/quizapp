package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestClickCheckResult extends SeleniumModel {
	
	public void testCheckResult() throws MalformedURLException{
		int counter =0;

		this.loginStudent();

		WebElement select = driver.findElement(By.name("type"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().equals("Satzteile")) {
				option.click();

				break;
			}
		}
		driver.findElement(By.name("filterExercises")).click();
		driver.findElement(By.name("solveExercise")).click();
		driver.findElement(By.name("checkResult")).click();
		counter++;

		assertTrue(counter <= 2);

	}

	/*
	 * Falls weitere Nodes hinzugef�gt werden
	 * 
	 * @Test public void testClickCheckResultFirefox() throws
	 * MalformedURLException{ this.setUpFirefox(); this.testCheckResult();
	 * driver.quit(); }
	 */
	@Test
	public void testClickCheckResultChrome() throws MalformedURLException {
		this.setUpChrome();
		this.testCheckResult();
		driver.quit();
	}

}
