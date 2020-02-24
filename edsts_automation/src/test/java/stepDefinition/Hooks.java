package stepDefinition;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import helper.Config;

public class Hooks extends AbstractTestNGCucumberTests {
	public static String browsers;

	@Parameters({ "browser","env" ,"siteUrl"})
	@BeforeTest
	public void setUpScenario(String browser, String env, String siteUrl) {
		browsers = browser;
		Config.Environment = env;
		Config.siteUrl = siteUrl;
	}
}
