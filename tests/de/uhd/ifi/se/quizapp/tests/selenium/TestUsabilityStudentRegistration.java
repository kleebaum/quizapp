package de.uhd.ifi.se.quizapp.tests.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;


public class TestUsabilityStudentRegistration extends SeleniumModel{


	public void testStudentRegistration() throws Exception {
		driver.navigate().to(this.baseUrl + "student/index.jsp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("register")).click();

		assertTrue(compareString("sie brauchen einen account und muessen eingeloggt sein, um aufgaben zu bearbeiten.",
				driver.findElement(By.cssSelector("h3")).getText()));
	}

	/*
	 * Falls weitere Nodes hinzugef�gt werden
	 * 
	 * @Test public void testStudentRegistrationFirefox() throws Exception{
	 * this.setUpFirefox(); this.testStudentRegistration(); driver.quit(); }
	 */
	@Test
	public void testStudentRegistrationChrome() throws Exception {
		this.setUpChrome();
		this.testStudentRegistration();
		driver.quit();
	}
}
