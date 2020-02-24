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


public class SearchPageSteps extends baseDriver {

	TestContext testContext;
	LoginPage loginPage;
	SearchPage searchPage;
	//SignOutPage signoutpage;
	Map<String, String> testData = new HashMap<>();
	String entityName = "";
	String EditISIN = "";

	public SearchPageSteps(TestContext context) {
		testContext = context;

	}

	
	@And("^user Enter product in search details page$")
	public void searchProductFromSearchBox() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);

		searchPage = pageObjectManager.getSearchPage();
		searchPage.searchProduct(testData);
	}

	@And("^user Click search button in search details page$")
	public void clickSearchData() {
		//testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);

		searchPage = pageObjectManager.getSearchPage();
		searchPage.clickSearchButton();

	}

	@Then("^user verify search result displayed in list and click the search data$")
	public void verifyAndClickSearchData() {
		//testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);

		searchPage = pageObjectManager.getSearchPage();
		searchPage.verifyClickSearchData();

	}
}