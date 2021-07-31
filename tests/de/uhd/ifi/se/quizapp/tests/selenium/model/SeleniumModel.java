package de.uhd.ifi.se.quizapp.tests.selenium.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumModel {

	protected WebDriver driver;
	protected String baseUrl;
	protected String nodeUrl;
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();

	public void setUpFirefox() throws MalformedURLException {

		this.baseUrl = "localhost:8080/";
		this.nodeUrl = " http://129.206.78.158:555/wd/hub";

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		// Nutzung von Firefox
		capability.setBrowserName("firefox");
		// Nutzung von Linux
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(this.nodeUrl), capability);
	}

	public void setUpChrome() throws MalformedURLException {
		this.baseUrl = "http://heieducation.ifi.uni-heidelberg.de:8080/nawidaztest/";
		this.nodeUrl = "http://129.206.78.158:5555/wd/hub";

		DesiredCapabilities capability = DesiredCapabilities.chrome();
		// Nutzung von Chrome
		capability.setBrowserName("chrome");
		// Nutzung von Linux
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(this.nodeUrl), capability);
	}

	protected void loginStudent() {
		driver.navigate().to(this.baseUrl + "student/index.jsp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("123");

		driver.findElement(By.name("login")).click();
	}

	protected void loginAdmin() {
		driver.navigate().to(this.baseUrl + "admin/index.jsp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("123");

		driver.findElement(By.name("login")).click();
	}

	protected boolean compareString(String expected, String actual) {
		actual = normaliseString(actual, 0);
		if (expected.equals(actual)) {
			return true;
		}
		return false;
	}

	private String normaliseString(String input, int from) {
		input = input.toLowerCase();
		for (int i = from; i < input.length(); i++) {
			char caracter = input.charAt(i);
			if (caracter == '[' || caracter == ']') {
				input = input.substring(0, i) + input.substring(i + 1);
				input = normaliseString(input, i);
				break;
			}
			if (caracter == '\u00fc') {
				input = input.substring(0, i) + "ue" + input.substring(i + 1);
				input = normaliseString(input, i);
				break;
			}
		}
		return input;
	}
}
