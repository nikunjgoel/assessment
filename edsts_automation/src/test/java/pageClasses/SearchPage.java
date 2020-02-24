package pageClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Config;
import helper.Constants;
import helper.GenUtility;
import helper.TestBase;

public class SearchPage extends TestBase {

	WebDriver driver;
	ExtentTest test;
	@FindBy(how = How.XPATH, using = Constants.searchbox_xpath) //
	public WebElement searchBox;
	@FindBy(how = How.XPATH, using = Constants.searchbutton_xpath) //
	public WebElement searchbutton;
	@FindBy(how = How.XPATH, using = Constants.result_xpath) //
	public WebElement searchResult;

	public SearchPage(WebDriver driver, ExtentTest test) {
		super(driver,test);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);

	}

	public void searchProduct(Map<String, String> testData) {
		try {
			sendKeys(searchBox, testData.get("Product"));
		}  catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Searchfailed with following exception: "+ e.getMessage());
			test.log(LogStatus.FAIL,
					"Screenshot below: " + test.addScreenCapture(GenUtility.takeScreenshot(driver, "SearchFailed")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Searchfailed with following exception: "+ e.getMessage());
		}

	}

	public void clickSearchButton() {

		try {
			clickElement(searchbutton);
		} catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Searchfailed with following exception: "+ e.getMessage());
			test.log(LogStatus.FAIL,
					"Screenshot below: " + test.addScreenCapture(GenUtility.takeScreenshot(driver, "SearchFailed")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Searchfailed with following exception: "+ e.getMessage());
		}

	}
	public void verifyClickSearchData(){
		try {
		Assert.assertTrue(isDisplayed(searchResult));
		clickElement(searchResult);
		} catch (Exception | AssertionError e) {
			test.log(LogStatus.FAIL, "Searchfailed with following exception: "+ e.getMessage());
			test.log(LogStatus.FAIL,
					"Screenshot below: " + test.addScreenCapture(GenUtility.takeScreenshot(driver, "SearchFailed")));
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Searchfailed with following exception: "+ e.getMessage());
		}

}
}