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

public class checkoutPageSteps extends baseDriver {

	CheckoutPage checkOutPage;
	TestContext testContext;
	String edCode = "";
	String iSIN = "";
	String entityName = "";
	String securitizationIdentifier;
	String currentdate;
	String parentProgram;
	Map<String, String> testData = new HashMap<>();

	public checkoutPageSteps(TestContext context) {
		testContext = context;
	}

	@Then("^user checkout the selected item from checkout page$")
	public void checkoutItem() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		checkOutPage = pageObjectManager.getCheckOutPage();
		checkOutPage.addItemToCheckout(testData);

	}
	
}
