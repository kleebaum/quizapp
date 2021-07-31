package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestMultipleRegistration extends SeleniumModel {
	
	public void testMultipleRegistration(){
		driver.navigate().to(this.baseUrl+"student/index.jsp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		/*
		 *Have to be Fixed in the Deploy
		 *At the moment the Elements have no id and cant be set
		 */
		
		/*
		driver.findElement(By.id("username1")).sendKeys("t");

		driver.findElement(By.id("username2")).sendKeys("t");
		driver.findElement(By.id("username3")).sendKeys("t");
		driver.findElement(By.id("password1")).sendKeys("t");
		driver.findElement(By.id("password2")).sendKeys("t");
		driver.findElement(By.name("register")).click();
		
		assertEquals("Registrierung nicht erfolgreich",
				driver.findElement(By.cssSelector("h3")).getText());
		*/
	}
	
	@Test
	public void testMultipleRegistationChrome() throws MalformedURLException{
		this.setUpChrome();
		this.testMultipleRegistration();
		driver.quit();
	}

}
