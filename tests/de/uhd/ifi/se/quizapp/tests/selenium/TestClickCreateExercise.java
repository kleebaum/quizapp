package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestClickCreateExercise extends SeleniumModel {
	
	public void testCreateExercise(){
		int counter =1;
		
		this.loginAdmin();
		counter++;
		
		driver.navigate().to(this.baseUrl+"admin/index.jsp?p=createExercise&type=1");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	    counter++;
	    
	    driver.findElement(By.name("input00")).clear();
	    driver.findElement(By.name("input00")).sendKeys("1");
	    
	    driver.findElement(By.name("radio00")).click();
	    counter++;
	    
	    driver.findElement(By.name("createExercise")).click();
	    counter++;
	    
	    assertTrue(counter<=7);
	}
	/*
	 * Falls weitere Nodes hinzugefügt werden 
	@Test
	public void testCreateExerciseFirefox() throws MalformedURLException{
		this.setUpFirefox();
		this.testCreateExercise();
		driver.quit();
	}
	*/
	@Test
	public void testCreateExerciseChrome() throws MalformedURLException{
		this.setUpChrome();
		this.testCreateExercise();
		driver.quit();
	}
}
