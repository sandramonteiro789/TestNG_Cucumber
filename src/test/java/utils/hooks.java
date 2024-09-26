package utils;

import java.io.IOException;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.*;

public class hooks extends BaseClass {
	
	@Before
	public void setUp(Scenario scenario) throws IOException {

		//Retrieve browser name from property file
		String browser = confReader.getBrowserType();

		// Set scenario and browser name in ThreadContext for log4j2
		ThreadContext.put("scenarioName", scenario.getName());
		ThreadContext.put("browser", browser);

		//Initialize WebDriver before each scenario according to browser in property file
		BaseClass.initializeDriver(browser);
		BaseClass.getDriver().manage().window().maximize();

	}

	@After
	public void tearDown() {
		// Quit WebDriver after each scenario
		BaseClass.quitDriver();
		ThreadContext.clearMap();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {

		//take screenshot after each step to add it in report
		WebDriver driver = BaseClass.getDriver();

		byte[] screenshotInByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshotInByte, "image/png", "image");

	}

}