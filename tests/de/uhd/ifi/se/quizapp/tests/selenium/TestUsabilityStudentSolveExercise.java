package de.uhd.ifi.se.quizapp.tests.selenium;

import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;


import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class TestUsabilityStudentSolveExercise extends SeleniumModel {
	  
	public void testStudentSolveExercise() throws Exception {
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
		
		assertTrue(compareString("fuelle alle satzteile aus!",driver.switchTo().alert().getText()));
		
	}

	/*
	 * Falls weitere Nodes hinzugef�gt werden
	 * 
	 * @Test public void testStudentSolveExerciseFirefox() throws Exception{
	 * this.setUpFirefox(); this.testStudentSolveExercise(); driver.quit(); }
	 */
	@Test
	public void testStudentSolveExerciseChrome() throws Exception {
		this.setUpChrome();
		this.testStudentSolveExercise();
		driver.quit();
	}
}
