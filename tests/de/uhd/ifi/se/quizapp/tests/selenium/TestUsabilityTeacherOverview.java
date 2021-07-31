package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.Test;
import org.openqa.selenium.By;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestUsabilityTeacherOverview extends SeleniumModel {

	public void testTeacherOverview() {
		driver.navigate().to(this.baseUrl + "/teacher/index.jsp?p=showStudents");
		assertTrue(compareString("schueler/innen-uebersicht", driver.findElement(By.cssSelector("h1")).getText()));

	}

	@Test
	public void testTeacherOverviewChrome() throws MalformedURLException {
		this.setUpChrome();
		this.testTeacherOverview();
		driver.quit();
	}
}
