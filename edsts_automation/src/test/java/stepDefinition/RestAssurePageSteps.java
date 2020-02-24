package stepDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.LogStatus;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import helper.TestBase;
import managers.PageObjectManager;
import pageClasses.CheckoutPage;
import pageClasses.DelieveryPage;
import pageClasses.RestAssurePage;
import pageClasses.ReviewPage;

public class RestAssurePageSteps extends baseDriver {

	RestAssurePage restAssurePage;
	TestContext testContext;
	String delieverytokenmessage = "";
	String timemessage = "";
	Map<String, String> testData = new HashMap<>();

	public RestAssurePageSteps(TestContext context) {
		testContext = context;
	}

	@And("^user get access token using ouath in rest details page$")
	public void checkaccessToken() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		restAssurePage = pageObjectManager.getRestAssurePage();
		String token=restAssurePage.verifyAPIData(testData);
		testContext.scenarioContext.setContext(Context.TOKEN, token);
		
		}
	
	@And("^user verify the get API using checkUserInfoContainsUserId$")
	public void checkUserInfoContainsUserId() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		delieverytokenmessage = testContext.scenarioContext.getContext(Context.TOKEN).toString();
		
		restAssurePage = pageObjectManager.getRestAssurePage();
		restAssurePage.checkUserInfoContainsUserId(testData,delieverytokenmessage);
	
		}
	
	@And("^user verify the get API using checkNumberOfAssociatedPaymentsIsEqualToZero$")
	public void checkNumberOfAssociatedPaymentsIsEqualToZero() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		delieverytokenmessage = testContext.scenarioContext.getContext(Context.TOKEN).toString();
		
		restAssurePage = pageObjectManager.getRestAssurePage();
		restAssurePage.checkNumberOfAssociatedPaymentsIsEqualToZero(testData,delieverytokenmessage);
	
		}
	
	/*@And("^user verify basic authentication$")
	public void checBasicAuthenticate() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		delieverytokenmessage = testContext.scenarioContext.getContext(Context.TOKEN).toString();
		
		restAssurePage = pageObjectManager.getRestAssurePage();
		restAssurePage.checkBasicAuthentication(testData);
	
		}*/
}
