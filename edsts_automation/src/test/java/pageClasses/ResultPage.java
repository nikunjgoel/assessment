package pageClasses;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Config;
import helper.Constants;
import helper.TestBase;

public class ResultPage extends TestBase {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;
	public ResultPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
	}

	@FindBy(how = How.XPATH, using = Constants.checklist_xpath)
	public WebElement checklist;
	@FindBy(how = How.XPATH, using = Constants.basket_xpath)
	public WebElement basket;
	@FindBy(how = How.XPATH, using = Constants.size_xpath)
	public WebElement size;
	@FindBy(how = How.XPATH, using = Constants.addtobasket_xpath)
	public WebElement addtoBasket;
	
	/**
	 * @description Add item to checklist
	 * @param testData
	 */
	public void addtoChecklist(Map<String, String> testData) {

		try {
			clickElementJS(size);
			clickElement(addtoBasket);
		    clickElement(basket);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Add to basket failed with following exception: " + e.getMessage());
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Add to basket failed with following exception: " + e.getMessage());
		}

	}
	
	/**
	 * @description Add item to basket
	 * @param testData
	 */
	public void addtoBasket(Map<String, String> testData) {

		try {
		    clickElement(basket);
		} catch (Exception e) {
	     	test.log(LogStatus.FAIL, "Add to basket failed with following exception: " + e.getMessage());
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Add to basket failed with following exception: " + e.getMessage());
		}

	}
	/**
	 * @ description Add size
	 * @param testData
	 */
	public void addsize(Map<String, String> testData) {

		try {
		    clickElement(size);
		} catch (Exception e) {
			System.out.println("Add to basket failed with following exception: " + e.getMessage());
			test.log(LogStatus.FAIL, "Add to basket failed with following exception: " + e.getMessage());
			driver.close();
			_extent.endTest(test);
			_extent.flush();
			Assert.fail("Add to basket failed with following exception: " + e.getMessage());
		}

	}	
}
