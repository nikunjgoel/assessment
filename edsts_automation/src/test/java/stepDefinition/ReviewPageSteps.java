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
import pageClasses.ReviewPage;

public class ReviewPageSteps extends baseDriver {

	ReviewPage reviewPage;
	TestContext testContext;
	String delieverymessage = "";
	String timemessage = "";
	Map<String, String> testData = new HashMap<>();

	public ReviewPageSteps(TestContext context) {
		testContext = context;
	}

	@And("^user verify the Delivery date and time on the Review and pay page.$")
	public void checkoutItem() {
		testData = (Map<String, String>) testContext.scenarioContext.getContext(Context.TEST_DATA);
		delieverymessage = testContext.scenarioContext.getContext(Context.PRODUCT_Date).toString();
		timemessage = testContext.scenarioContext.getContext(Context.PRODUCT_Time).toString();
		
		reviewPage = pageObjectManager.getReviewPage();
		reviewPage.verifyDateTimefromDelieveryPage(testData,delieverymessage,timemessage);
		}
	@And("^quit from the application$")
	public void quitdriver() {
		reviewPage = pageObjectManager.getReviewPage();
		reviewPage.quitDriver();
		
	}
	
}
