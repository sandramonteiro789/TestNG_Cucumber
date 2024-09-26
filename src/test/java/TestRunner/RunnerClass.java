package TestRunner;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.ConfigFileReader;

@RunWith(Cucumber.class)
@CucumberOptions(
					features= {"src/test/resources/Features/Login.feature"},
					glue={"StepDefs","utils"},
					plugin= {"pretty", "html:reports/myreport.html",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
							"timeline:test-output-thread/"},		
					dryRun=false,    // checks mapping between scenario steps and step definition methods
					monochrome=true,    // to avoid junk characters in output
					publish=true   // to publish report in cucumber server
		)

public class RunnerClass extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
	
	@BeforeMethod
	@Parameters({"browser"})
	public void defineBrowser(String browser) throws Throwable{
		ConfigFileReader configFileReader = new ConfigFileReader();
		configFileReader.setBrowserType(browser);
		System.out.println(browser);
	}
}