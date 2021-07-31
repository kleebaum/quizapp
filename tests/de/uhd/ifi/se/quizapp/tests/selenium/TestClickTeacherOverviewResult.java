package de.uhd.ifi.se.quizapp.tests.selenium;

import static org.junit.Assert.assertTrue;
import org.junit.*;

import de.uhd.ifi.se.quizapp.tests.selenium.model.SeleniumModel;

public class TestClickTeacherOverviewResult extends SeleniumModel {
	

	public void testClickTeacherReuslt() throws Exception {
		int maxClicks=2;
		int counter1=0;
		int counter2=0;
		
		driver.navigate().to(this.baseUrl + "student/index.jsp");
		driver.navigate().to(this.baseUrl + "teacher/index.jsp");
		counter1++;
		counter2++;
		
		driver.navigate().to(this.baseUrl + "teacher/index.jsp?p=showStudents");
		counter1++;
		
		//driver.navigate().to(this.baseUrl + "teacher/index.jsp?p=showExercises");
		counter2++;
		
		if(counter1>=counter2){
				assertTrue(counter1<=maxClicks);				
		}
		else {
			assertTrue(false);
		}
		if(counter2>=counter1) {
			assertTrue(counter2<=maxClicks);
		}
		else {
			assertTrue(false);
		}
	}
	/*
	 * Falls weitere Nodes hinzugef�gt werden 
	@Test
	public void testClickTeacherReusltFirefox() throws Exception{
		this.setUpFirefox();
		this.testClickTeacherReuslt();
		driver.quit();
	}
	*/
	@Test 
	public void testClickTeacherReusltChrome() throws Exception{
		this.setUpChrome();
		this.testClickTeacherReuslt();
		driver.quit();
	}
}
