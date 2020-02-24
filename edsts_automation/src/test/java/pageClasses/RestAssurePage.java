package pageClasses;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Config;
import helper.Constants;
import helper.GenUtility;
import helper.TestBase;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
public class RestAssurePage extends TestBase {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;

	public RestAssurePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = Constants.reviewPageMessage_xpath)
	public WebElement reviewMessage;
/**
 * @author nikgoel1
 * @description verify time and message
 * @param testData
 * @param delieverymessage
 * @param timemessage
 */
	String accessToken ;
	public String verifyAPIData(Map<String, String> testData) {
		try {
			String response =
					given().
						parameters("grant_type","client_credentials").
						auth().
						preemptive().
						basic("AUyqLmmlHyX4Th7BdXpIN-sKu5rARNpWLNtQZabRneRp5eDrKEU5pdiNIOMgc-4OiNu4jX8VJwfwWr1a","ECFXJmz2yW0WDf0itUE13jgaBhLkF5kEV9pyzt8iK9vvWgoSBRQ0HCywNIqYftSwXmB6EH_KOGq0nO39").
					when().
						post("https://api.sandbox.paypal.com/v1/oauth2/token").
						asString();

			JsonPath jsonPath = new JsonPath(response);

			 accessToken = jsonPath.getString("access_token");
			 test.log(LogStatus.PASS, "Get OAUTH verification process passed successfully");
		} catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Review verification process failed with following exception:" + e.getMessage());
			test.log(LogStatus.FAIL, "Screenshot below: "
					+ test.addScreenCapture(GenUtility.takeScreenshot(driver, "ReviewsVerification")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Review verification process failed with following exception:" + e.getMessage());
		}
		return accessToken;
	}
	public void checkUserInfoContainsUserId(Map<String, String> testData , String token) {
		try {
			given().
			contentType("application/json").
			auth().
			oauth2(token).
		when().
			get("https://api.sandbox.paypal.com/v1/identity/openidconnect/userinfo/?schema=openid").
		then().
			assertThat().
			body("",hasKey("user_id"));
			test.log(LogStatus.PASS, "OAUTH verification process passed successfully");
			
		} catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Review verification process failed with following exception:" + e.getMessage());
			test.log(LogStatus.FAIL, "Screenshot below: "
					+ test.addScreenCapture(GenUtility.takeScreenshot(driver, "ReviewsVerification")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Review verification process failed with following exception:" + e.getMessage());
		}
	}
	
	public void checkNumberOfAssociatedPaymentsIsEqualToZero(Map<String, String> testData , String token) {
		try {
			given().
			contentType("application/json").
			auth().
			oauth2(token).
		when().
			get("https://api.sandbox.paypal.com/v1/payments/payment/").
		then().
			assertThat().
			body("count", equalTo(0));
			test.log(LogStatus.PASS, "OAUTH verification process passed successfully");
		} catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Review verification process failed with following exception:" + e.getMessage());
			test.log(LogStatus.FAIL, "Screenshot below: "
					+ test.addScreenCapture(GenUtility.takeScreenshot(driver, "ReviewsVerification")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Review verification process failed with following exception:" + e.getMessage());
		}
	}
	
}
