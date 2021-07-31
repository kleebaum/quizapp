package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestSubmitSolutionWithoutFilling extends SeleniumModel {

	public void studentSubmitSoulutionWithoutFilling(){
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
	
	@Test
	public void testStudentSubmitSoulutionWithoutFilling() throws MalformedURLException{
		this.setUpChrome();
		this.studentSubmitSoulutionWithoutFilling();
		driver.quit();
	}
}
