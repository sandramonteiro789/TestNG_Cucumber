package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	public static By registerLink=By.xpath("//a[text()='Register']");
	public static By firstNameTextBox=By.xpath("//*[@id='customer.firstName']");
	public static By lastNameTextBox=By.xpath("//*[@id='customer.lastName']");
	public static By addressTextBox=By.xpath("//*[@id='customer.address.street']");
	public static By cityTextBox=By.xpath("//*[@id='customer.address.city']");
	public static By stateTextBox=By.xpath("//*[@id='customer.address.state']");
	public static By zipTextBox=By.xpath("//*[@id='customer.address.zipCode']");
	public static By phoneTextBox=By.xpath("//*[@id='customer.phoneNumber']");
	public static By ssnTextBox=By.xpath("//*[@id='customer.ssn']");
	public static By userNameTextBox=By.xpath("//*[@id='customer.username']");
	public static By passwordTextBox=By.xpath("//*[@id='customer.password']");
	public static By confirmTextBox=By.xpath("//*[@id='repeatedPassword']");
	public static By registerButton=By.xpath("//input[@value='Register']");
} 