package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Config;
import helper.Constants;
import helper.GenUtility;
import helper.TestBase;

public class HomePage extends TestBase {

	WebDriver driver;
	ExtentTest test;
	@FindBy(how = How.ID, using = Constants.allowCookie_Id)
    public WebElement allowCookie;
	public HomePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
	}

	/**
	 * @nikgoel1
	 * @return
	 */
	public String HomePageNavigation() {
		try {

			driver.get(Config.siteUrl);
			clickElement(allowCookie);
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			test.log(LogStatus.FAIL,
					"Home Page not loaded" + e.getMessage());
			test.log(LogStatus.FAIL,
					"Screenshot below: " + test.addScreenCapture(GenUtility.takeScreenshot(driver, "HomePage")));
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Home page not get loaded" + e.getMessage());
		}
		return driver.getTitle();
	}

}
