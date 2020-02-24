package pageClasses;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;

import helper.Constants;
import helper.GenUtility;
import helper.TestBase;

public class DelieveryPage extends TestBase {

	WebDriver driver;
	ExtentTest test;

	public DelieveryPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 20), this);

	}

	@FindBy(how = How.XPATH, using = Constants.titledelivery_xpath)
	public WebElement title;
	@FindBy(how = How.XPATH, using = Constants.firstnamedel_xpath)
	public WebElement fname;
	@FindBy(how = How.XPATH, using = Constants.lastnamedel_xpath)
	public WebElement lname;
	@FindBy(how = How.XPATH, using = Constants.phonedelivery_xpath)
	public WebElement phone;
	@FindBy(how = How.XPATH, using = Constants.addressdel_xpath)
	public WebElement address;
	@FindBy(how = How.XPATH, using = Constants.addressbutton_xpath)
	public WebElement addressbutton;
	@FindBy(how = How.XPATH, using = Constants.useaddressDelivery_xpath)
	public WebElement useaddressbutton;
	@FindBy(how = How.XPATH, using = Constants.deliverynext_xpath)
	public WebElement delradionext;
	@FindBy(how = How.XPATH, using = Constants.seconddaydate_xpath)
	public WebElement seconddaydate;
	@FindBy(how = How.XPATH, using = Constants.messagedeliverydateSuccess_xpath)
	public WebElement messagedel;
	@FindBy(how = How.XPATH, using = Constants.continuebuttonpayment_xpath)
	public WebElement continuePayment;
	@FindBy(how = How.XPATH, using = Constants.delieverPageMessagetime_xpath)
	public WebElement messageTime;

	/**
	 * @author nikgoel1
	 * @description add delievery detail
	 * @param testData
	 * @return
	 */
	public String addDelieveryDetails(Map<String, String> testData) {
		try {
			sendKeys(title, testData.get("Title"));
			sendKeys(fname, testData.get("Firstname"));
			sendKeys(lname, testData.get("LastName"));
			sendKeys(phone, testData.get("PhoneNumber"));
			sendKeys(address, testData.get("Address"));
			clickAndWait(addressbutton);
			clickElement(useaddressbutton);
			clickElementJS(delradionext);
			clickElement(seconddaydate);
			getText(messagedel);
		} catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Fail to add delievery method" + e.getMessage());
			test.log(LogStatus.FAIL,
					"Screenshot below: " + test.addScreenCapture(GenUtility.takeScreenshot(driver, "DelieveryPage")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("There is something wrong in Delievery verification process: " + e.getMessage());
		}
		return getText(messagedel);
	}

	/**
	 * @description Get time of delievery
	 * @param testData
	 * @return
	 */
	public String getTimeDetails(Map<String, String> testData) {
		try {
			getText(messageTime);
			clickElement(continuePayment);
		} catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Fail to get delievery detail" + e.getMessage());
			test.log(LogStatus.FAIL,
					"Screenshot below: " + test.addScreenCapture(GenUtility.takeScreenshot(driver, "DelieveryDetail")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("There is something wrong in Delievery verification process: " + e.getMessage());
		}
		return getText(messageTime);
	}

}
