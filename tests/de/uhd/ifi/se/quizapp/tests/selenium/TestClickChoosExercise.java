package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;


public class TestClickChoosExercise extends SeleniumModel{


	public void testClickChoosExercise() throws Exception {
		int counter = 0;
		this.loginStudent();
		counter++;

		WebElement select = driver.findElement(By.name("type"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().equals("Richtig/Falsch")) {
				option.click();
				counter++;
				break;
			}
		}

		driver.findElement(By.name("filterExercises")).click();
		counter++;
		assertTrue(counter <= 3);
	}

	/*
	 * Falls weitere Nodes hinzugef�gt werden
	 * 
	 * @Test public void testClickChoosExerciseFirefox() throws Exception{
	 * this.setUpFirefox(); this.testClickChoosExercise(); driver.quit(); }
	 */
	@Test
	public void testClickChoosExerciseChrome() throws Exception {
		this.setUpChrome();
		this.testClickChoosExercise();
		driver.quit();
	}
}
