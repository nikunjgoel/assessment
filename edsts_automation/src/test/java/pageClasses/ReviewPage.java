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

public class ReviewPage extends TestBase {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;

	public ReviewPage(WebDriver driver, ExtentTest test) {
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
	public void verifyDateTimefromDelieveryPage(Map<String, String> testData, String delieverymessage,
			String timemessage) {
		try {
			String reviewmessage = getText(reviewMessage);
			String[] delmessagearr = delieverymessage.split("on");
			System.out.println(delmessagearr[1]);
			String[] revmessagearr = reviewmessage.split("on");
			System.out.println(revmessagearr[1]);
			String Actualdelmessage=delmessagearr[1].replace("2020"," ").trim();
			Assert.assertTrue(revmessagearr[1].contains(Actualdelmessage));
			
			String[] arrSplit = timemessage.split("week");
		      System.out.println(arrSplit[1]);
		      String Actualtime=arrSplit[1].replace("(excluding public holidays). Order by 8pm for delivery following day. Other exclusions may apply.", " ").trim();
		     System.out.println(Actualtime);
		     Assert.assertTrue(revmessagearr[1].contains(Actualtime));

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
	public void quitDriver(){
		driver.quit();
	}
}
