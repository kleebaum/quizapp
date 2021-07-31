package de.uhd.ifi.se.quizapp.tests.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestUsabilityAdministratorCreateExervise extends SeleniumModel {

	public void testAdministratorCreateExercise() throws Exception {
		this.loginAdmin();

		driver.navigate().to(this.baseUrl + "/admin/index.jsp?p=createExercise&type=1");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.name("input00")).clear();
		driver.findElement(By.name("input00")).sendKeys("1");
		driver.findElement(By.name("createExercise")).click();
		assertEquals("Administrator/Innen Zugang", driver.getTitle());
	}

	/*
	 * Falls weitere Nodes hinzugefügt werden
	 * 
	 * @Test public void testAdministratorCreateExervisFirefox() throws
	 * Exception{ this.setUpFirefox(); this.testAdministratorCreateExervise();
	 * driver.quit(); }
	 */
	@Test
	public void testAdministratorCreateExervisChrome() throws Exception {
		this.setUpChrome();
		this.testAdministratorCreateExercise();
		driver.quit();
	}
}
