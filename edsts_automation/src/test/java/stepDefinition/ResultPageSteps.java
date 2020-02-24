package stepDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import enums.Context;
import pageClasses.SearchPage;
import pageClasses.LoginPage;
import pageClasses.ResultPage;


public class ResultPageSteps extends baseDriver {

	TestContext testContext;
	LoginPage loginPage;
	ResultPage resultPage;
	//SignOutPage signoutpage;
	Map<String, String> testData = new HashMap<>();
	String entityName = "";
	String EditISIN = "";

	public ResultPageSteps(TestContext context) {
		testContext = context;

	}

	
	@And("^user add selected item to checklist from result page$")
	public void searchProductFromSearchBox() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);

		resultPage = pageObjectManager.getResultPage();
		resultPage.addtoChecklist(testData);
	}

}