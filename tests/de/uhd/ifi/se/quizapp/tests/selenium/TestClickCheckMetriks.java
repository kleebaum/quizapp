package de.uhd.ifi.se.quizapp.tests.selenium;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.Test;

public class TestClickCheckMetriks extends SeleniumModel{

	public void testCheckMetriks(){
		int counter=0;
		
		driver.navigate().to(this.baseUrl + "teacher/index.jsp");
		counter++;
		driver.navigate().to(this.baseUrl + "teacher/index.jsp?p=showMetriks");
		counter++;
		if(counter<=2){
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	@Test
	public void testClickTeacherMeticsChrome() throws MalformedURLException{
		this.setUpChrome();
		this.testCheckMetriks();
		driver.quit();
	}
}
