package StepDefs;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObjects.HomePage;
import io.cucumber.java.en.*;
import utils.BaseClass;
import utils.ConfigFileReader;
import utils.ExcelUtils;
import io.cucumber.datatable.DataTable;

public class LoginStepDefs extends BaseClass {
	
	WebDriver driver = BaseClass.getDriver();
	public static Logger log = LogManager.getLogger(LoginStepDefs.class);
	static ExcelUtils excel = new ExcelUtils(System.getProperty("user.dir")+"\\TestData\\testdata.xlsx");
	HomePage hmpage=new HomePage(driver);
	public static ConfigFileReader confReader = new ConfigFileReader();

	@Given("User enters the Parabank URL in the browser")
	public void user_enters_the_parabank_url_in_the_browser() throws IOException {
		driver.get(confReader.getApplicationUrl()); // reading URL from properties file.
		driver.manage().window().maximize();
		String actualTitle=driver.getTitle();
		String expectedTitle=excel.getCellData("login", 0, 1);
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("User has directed to Parabank home page");
	}

	@When("User clicks on register URL")
	public void user_clicks_on_register_url() throws IOException {
	    
		driver.findElement(HomePage.registerLink).click();
		String actualTitle=driver.getTitle();
		String expectedTitle=excel.getCellData("login", 1, 1);
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("User has directed to Parabank registration page");
	}

	@Then("User enters the user information")
	public void user_enters_the_user_information(DataTable usercredentials) throws IOException {
		
		List<List<String>> data = usercredentials.cells();
		
		String firstName=excel.getCellData("login", data.get(0).get(0));
		driver.findElement(HomePage.firstNameTextBox).sendKeys(firstName);
		
		String lastName=excel.getCellData("login", data.get(0).get(1));
		driver.findElement(HomePage.lastNameTextBox).sendKeys(lastName);
		
		String address=excel.getCellData("login", data.get(0).get(2));
		driver.findElement(HomePage.addressTextBox).sendKeys(address);
		
		String city=excel.getCellData("login", data.get(0).get(3));
		driver.findElement(HomePage.cityTextBox).sendKeys(city);
		
		String state=excel.getCellData("login", data.get(0).get(4));
		driver.findElement(HomePage.stateTextBox).sendKeys(state);
		
		String zip=excel.getCellData("login", data.get(0).get(5));
		driver.findElement(HomePage.zipTextBox).sendKeys(zip);
		
		String phone=excel.getCellData("login", data.get(0).get(6));
		driver.findElement(HomePage.phoneTextBox).sendKeys(phone);
		
		String ssn=excel.getCellData("login", data.get(0).get(7));
		driver.findElement(HomePage.ssnTextBox).sendKeys(ssn);
		
		String userName=excel.getCellData("login", data.get(0).get(8));
		driver.findElement(HomePage.userNameTextBox).sendKeys(userName);
		
		String password=excel.getCellData("login", data.get(0).get(9));
		driver.findElement(HomePage.passwordTextBox).sendKeys(password);
		
		String confirm=excel.getCellData("login", data.get(0).get(10));
		driver.findElement(HomePage.confirmTextBox).sendKeys(confirm);
		log.info("User enters all user details");
		
	}

	@Then("User clicks on register Button")
	public void user_clicks_on_register_button() {
	    
		driver.findElement(HomePage.registerButton).click();
		log.info("User clicks on register button");
	}
}