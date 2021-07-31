package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestLoginWithUsernamePasswordNull extends SeleniumModel{ 
	
	public void testLoginNullExpression(){
		driver.navigate().to(this.baseUrl + "student/index.jsp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		driver.findElement(By.name("username")).clear();		
		driver.findElement(By.name("password")).clear();
		
		driver.findElement(By.name("login")).click();
		assertTrue(compareString("sie brauchen einen account und muessen eingeloggt sein, um aufgaben zu bearbeiten.", driver.findElement(By.cssSelector("h3")).getText()));
	}
	
	@Test
	public void testLoginNullExpressionChrome() throws MalformedURLException{
		this.setUpChrome();
		this.testLoginNullExpression();
		driver.quit();
	}
}
