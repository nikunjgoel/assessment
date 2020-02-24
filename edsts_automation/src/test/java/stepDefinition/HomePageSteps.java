package stepDefinition;

import java.util.Map;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import enums.Context;
import helper.fetchDatafromExcelColumnWise;
import pageClasses.HomePage;

public class HomePageSteps extends baseDriver {

	HomePage homePage;
	TestContext testContext;


	public HomePageSteps(TestContext context) {
		testContext = context;

	}

	@Given("^User is on Home Page \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_is_on_Home_Page(String TestCaseName, String TestCaseSheetName) throws Exception {
		testContext.scenarioContext.setContext(Context.TEST_DATA,
				new fetchDatafromExcelColumnWise().getTestData(TestCaseName, TestCaseSheetName));
		Map<String, String> testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		String testCaseName = testData.get("TestType");
		System.out.println(testData.get("TestType"));
		BaseDriver(testCaseName);
		homePage = pageObjectManager.getHomePage();
		if(!(testData.get("TestType").equalsIgnoreCase("API"))){
		
	    homePage.HomePageNavigation();
		}
	}

}
