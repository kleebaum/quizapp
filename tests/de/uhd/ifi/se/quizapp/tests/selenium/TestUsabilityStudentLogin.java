package de.uhd.ifi.se.quizapp.tests.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestUsabilityStudentLogin extends SeleniumModel{


	public void testStudentLogin() throws Exception {
		driver.navigate().to(this.baseUrl + "student/index.jsp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("login")).click();

		assertTrue(compareString("sie brauchen einen account und muessen eingeloggt sein, um aufgaben zu bearbeiten.", driver.findElement(By.cssSelector("h3")).getText()));
	}
	/*
	 * Falls weitere Nodes hinzugef�gt werden 
	@Test
	public void testStudentLoginFirefox() throws Exception{
		this.setUpFirefox();
		this.testStudentLogin();
		driver.quit();
	}
	*/
	@Test
	public void testStudentLoginChrome() throws Exception{
		this.setUpChrome();
		this.testStudentLogin();
		driver.quit();
	}
}
