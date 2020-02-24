package stepDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.LogStatus;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import helper.TestBase;
import managers.PageObjectManager;
import pageClasses.CheckoutPage;
import pageClasses.DelieveryPage;

public class DelieveryPageSteps extends baseDriver {

	DelieveryPage delieveryPage;
	TestContext testContext;
	String edCode = "";
	String iSIN = "";
	String entityName = "";
	String securitizationIdentifier;
	String currentdate;
	String parentProgram;
	Map<String, String> testData = new HashMap<>();

	public DelieveryPageSteps(TestContext context) {
		testContext = context;
	}

	@Then("^user enter delivery details in delivery page$")
	public void checkoutItem() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		delieveryPage = pageObjectManager.getDelieveryPage();
		String timeMessage=delieveryPage.addDelieveryDetails(testData);
		String timeonlyMessage=delieveryPage.getTimeDetails(testData);
		testContext.scenarioContext.setContext(Context.PRODUCT_Date, timeMessage);
		testContext.scenarioContext.setContext(Context.PRODUCT_Time, timeonlyMessage);
	}
	
}
