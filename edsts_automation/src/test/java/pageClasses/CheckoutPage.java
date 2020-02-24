package pageClasses;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import helper.XlsReader;

public class CheckoutPage extends TestBase {
	ExtentTest test;
	WebDriverWait wait;
	XlsReader xlsReader;

	public CheckoutPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		wait = new WebDriverWait(driver, Config.webdriverWait);
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.XPATH, using = Constants.checkout_xpath)
	public WebElement checkout;
	@FindBy(how = How.XPATH, using = Constants.guestradio_xpath)
	public WebElement guest;
	@FindBy(how = How.XPATH, using = Constants.emailguests_xpath)
	public WebElement emailguest;
	@FindBy(how = How.XPATH, using = Constants.continueGuest_xpath)
	public WebElement continueasguest;
	@FindBy(how = How.XPATH, using = Constants.delievery_xpath)
	public WebElement delivery;
	
	/**
	 * @author nikgoel1
	 * @description add item to checklist
	 * @param testData
	 */
	public void addItemToCheckout(Map<String, String> testData) {
		try {
			clickElement(checkout);
			clickElementJS(guest);
			sendKeys(emailguest,testData.get("guestEmail"));
			clickElement(continueasguest);
			clickElement(delivery);
			test.log(LogStatus.PASS, "All the LLD and IR files downloaded successfully.");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Fail to add item:" + e.getMessage());
			test.log(LogStatus.FAIL, "Screenshot below: "
					+ test.addScreenCapture(GenUtility.takeScreenshot(driver, "CheckOutPage")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("There is something wrong in CheckOut process " + e.getMessage());
		}
	}

	
}
