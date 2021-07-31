package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestClickLoginInAfterLogOut extends SeleniumModel {

	public void testLoginInAfterLogOut() {
		int counter = 0;

		this.loginStudent();

		driver.navigate().to(this.baseUrl + "student/Student?logout=logout");

		driver.navigate().to(this.baseUrl + "student/index.jsp");
		counter++;

		this.loginStudent();
		counter++;

		assertTrue(counter <= 2);
	}

	/*
	 * Falls weitere Nodes hinzugef�gt werden
	 * 
	 * @Test public void testLoginInAfterLogOutFirefox() throws
	 * MalformedURLException{ this.setUpFirefox();
	 * this.testLoginInAfterLogOut(); driver.quit(); }
	 */
	@Test
	public void testLoginInAfterLogOutChrome() throws MalformedURLException {
		this.setUpChrome();
		this.testLoginInAfterLogOut();
		driver.quit();
	}
}
