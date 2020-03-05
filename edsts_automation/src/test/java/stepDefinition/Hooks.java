package stepDefinition;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import helper.Config;
import helper.ExtentManager;
public class Hooks extends AbstractTestNGCucumberTests {
	public static String browsers;
	ExtentTest test;
	@Parameters({ "browser","env" ,"siteUrl"})
	@BeforeTest
	public void setUpScenario(String browser, String env, String siteUrl) {
		browsers = browser;
		Config.Environment = env;
		Config.siteUrl = siteUrl;
	}
	
	@AfterTest
	public void generateReport() {
		ExtentManager._extent.endTest(test);
		ExtentManager._extent.flush();
	}
}
