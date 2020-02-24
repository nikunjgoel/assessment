package stepDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import enums.Context;
import helper.Config;
import helper.TestBase;
import pageClasses.LoginPage;

public class LoginPageSteps extends baseDriver {

	TestContext testContext;
	LoginPage loginPage;
	Map<String, String> testData = new HashMap<>();

	public LoginPageSteps(TestContext context) {
		testContext = context;
		loginPage = pageObjectManager.getLoginPage();

	}

	@When("^User Login with \"([^\"]*)\" Credentials$")
	public void userLoginwithCredentials(String User) {

		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		String env;

	   loginPage.login(testData.get("username"), testData.get("Password"));
	}
}
