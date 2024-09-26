package utils;

import java.io.IOException;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.*;

public class hooks extends BaseClass {

//	@Before
//	public void setUp() {
//		WebDriver driver = WebDriverFactory.getDriver();
//		// Perform setup actions with the driver
//	}
//
//	@After
//	public void tearDown() {
//		WebDriverFactory.quitDriver();
//	}

	@Before
	public void setUp(Scenario scenario) throws IOException {
		// Initialize WebDriver before each scenario

		ConfigFileReader configFileReader = new ConfigFileReader();
		String browserLog = configFileReader.getBrowserType();

	        // Set scenario name in ThreadContext
	        ThreadContext.put("scenarioName", scenario.getName());

	        // Set browser-specific logger
	        if ("chrome".equalsIgnoreCase(browserLog)) {
	            ThreadContext.put("browser", "Chrome");
	            log.info("Starting scenario on Chrome: " + scenario.getName());
	        } else if ("firefox".equalsIgnoreCase(browserLog)) {
	            ThreadContext.put("browser", "Firefox");
	            log.info("Starting scenario on Firefox: " + scenario.getName());
	        } else if ("edge".equalsIgnoreCase(browserLog)) {
	            ThreadContext.put("browser", "Edge");
	            log.info("Starting scenario on Edge: " + scenario.getName());
	        } 
	        
		ThreadContext.put("browser", browserLog);
		String browser = confReader.getBrowserType();
		BaseClass.initializeDriver(browser);
		BaseClass.getDriver().manage().window().maximize();

	}

    @After
    public void tearDown() {
        // Quit WebDriver after each scenario
    	BaseClass.quitDriver();
    	ThreadContext.clearAll();
    }

	@AfterStep
	public void addScreenshot(Scenario scenario) {
		
		WebDriver driver = BaseClass.getDriver();

		byte[] screenshotInByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshotInByte, "image/png", "image");

	}

}