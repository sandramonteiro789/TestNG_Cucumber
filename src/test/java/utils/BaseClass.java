package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j

public class BaseClass {

	public static Logger log = LogManager.getLogger(BaseClass.class);
	public static ConfigFileReader confReader = new ConfigFileReader();
	
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get the WebDriver for the current thread
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Set the WebDriver for the current thread
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    // Initialize WebDriver based on browser type (you can customize this)
    public static void initializeDriver(String browserParam) {
        WebDriver webDriver = null;

        if (browserParam.equalsIgnoreCase("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browserParam.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        }else if (browserParam.equalsIgnoreCase("edge")) {
            webDriver = new EdgeDriver();
        }
        setDriver(webDriver);
    }

    // Quit the WebDriver for the current thread
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();  // Clean up the thread-local storage
        }
    }
	
//	@BeforeTest
//	@Parameters({ "os", "browser" })
//	public void setup(String os, String br) throws IOException {
//
//		log.info("Tests are Starting!");
//		if (confReader.getExecutionEnv().equalsIgnoreCase("remote")) {
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//
//			// os
//			if (os.equalsIgnoreCase("windows")) {
//				capabilities.setPlatform(Platform.WIN11);
//			} else if (os.equalsIgnoreCase("linux")) {
//				capabilities.setPlatform(Platform.LINUX);
//
//			} else if (os.equalsIgnoreCase("mac")) {
//				capabilities.setPlatform(Platform.MAC);
//			} else {
//				System.out.println("No matching os");
//				return;
//			}
//
//			// browser
//			switch (br.toLowerCase()) {
//			case "chrome":
//				capabilities.setBrowserName("chrome");
//				break;
//			case "edge":
//				capabilities.setBrowserName("MicrosoftEdge");
//				break;
//			case "firefox":
//				capabilities.setBrowserName("firefox");
//				break;
//			default:
//				System.out.println("No matching browser");
//				return;
//			}
//
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//		}
//
//		if (confReader.getExecutionEnv().equalsIgnoreCase("local")) {
//
//			switch (br.toLowerCase()) {
//			case "chrome":
//				driver = new ChromeDriver();
//				break;
//			case "edge":
//				driver = new EdgeDriver();
//				break;
//			case "firefox":
//				driver = new FirefoxDriver();
//				break;
//			default:
//				System.out.println("Invalid browser name..");
//				return;
//			}
//		}
//
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	}
//
//	@AfterTest
//	public void tearDownBrowser() {
//		log.info("Tests are ending!");
//		driver.quit();
//	}

	public String randomeString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	public String randomeNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomeAlphaNumberic() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + "@" + generatednumber);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yy-MM-dd HH.mm.ss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}
}