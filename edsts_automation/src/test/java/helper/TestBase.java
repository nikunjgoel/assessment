package helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageClasses.LoginPage;

public class TestBase extends ExtentManager {

	public WebDriver driver = null;
	public DesiredCapabilities caps = null;
	public LoginPage _loginPage = null;
	public ExtentTest _test;

	public TestBase(WebDriver driver, ExtentTest _test) {
		this.driver = driver;
		this._test = _test;
	}

	public TestBase(ExtentTest _test) {
		this._test = _test;
	}

	public void change_download_setting() throws IOException {
		driver.get("chrome://settings/advanced");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String prefId = "download.default_directory";
		File tempDir = new File(System.getProperty("user.dir") + "\\Downloads\\");
		if (driver.findElements(By.xpath(String.format(".//input[@pref='%s']", prefId))).size() == 0) {
			driver.get("chrome://settings-frame");
			driver.findElement(By.xpath(".//*[@id='advanced-settings-expander']")).click();
		}
		String tmpDirEscapedPath = tempDir.getCanonicalPath().replace("\\", "\\\\");
		js.executeScript(String.format("Preferences.setStringPref('%s', '%s', true)", prefId, tmpDirEscapedPath));
		driver.navigate().back();
		driver.navigate().back();
	}

	public void checkPageIsReady(WebDriver _driver) {

		JavascriptExecutor js = (JavascriptExecutor) _driver;

		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			return;
		}

		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void quit_driver(WebDriver driver, ExtentTest test) {
		try {

			if (_extent != null) {
				_extent.endTest(_test);
				_extent.flush();
			}

			if (driver != null) {
				driver.close();
				// driver = null;

			}

		} catch (Throwable e) {

			_test.log(LogStatus.FAIL, "Browser is not closed successfully");
			_test.log(LogStatus.FAIL, "Screenshot below: "
					+ _test.addScreenCapture(GenUtility.takeScreenshot(driver, "EndTestClosingBrowser")));
			e.printStackTrace();
		}

	}

	public String currentdate(String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		Date today = new Date();
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String date = formatter.format(today);
		System.out.println("UTC Time is: " + date);
		return date;
	}

	public void waitForXSeconds(long milli) {

		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendKeys(WebElement ele, String text) {
		webdriverWaitVisibilityOf(ele, Config.webdriverWait);
		enterText(ele, text);

	}

	public void sendKeys(WebElement ele, String text, boolean isWaitReq) {
		if (isWaitReq)
			webdriverWaitVisibilityOf(ele, Config.webdriverWait);
		enterText(ele, text);

	}

	public void webDriverWaitAndVerityTextPresent(WebElement ele, String text) {
		webdriverWaitTextToBePresent(ele, Config.webdriverWait, text);

	}

	public void waitUntilInvisibilityOfLoader(int webdriverWait, String by) {
		new WebDriverWait(driver, webdriverWait)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(by)));

	}

	/*public void WaitUntilInvivisibilityOfLoader() {
		new WebDriverWait(driver, Config.webdriverWait).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("img[src='../../../../Content/css/images/loader.gif']")));

	}

	public void WaitUntilInvivisibilityOfLoading() {
		new WebDriverWait(driver, Config.webdriverWait).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("img[src='../../../../Content/css/images/loading.gif']")));

	}
*/
	public String getText(WebElement ele) {
		webdriverWaitUntilText(ele, Config.webdriverWait);
		return getElementText(ele);
	}

	public String webDriverWaitGetValue(WebElement ele, String text) {
		webdriverWaitTextToBePresent(ele, Config.webdriverWait, text);
		return getAttributeValue(ele);
	}

	public void webDriverWaitAndClick(WebElement ele) {

		webdriverWaitClickable(ele, Config.webdriverWait);
		clickElement(ele);
	}

	public void clickElement(WebElement ele) {
		scrollWindow(ele);
		ele.click();
		waitForXSeconds(5000);
	}

	public void clickAndWait(WebElement ele) throws InterruptedException {
		webdriverWaitClickable(ele, Config.webdriverWait);
		scrollWindow(ele);
		ele.click();
		waitForXSeconds(2000);
	}

	public void clickElementJS(WebElement ele) {
		waitForXSeconds(2000);
		scrollWindow(ele);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		waitForXSeconds(3000);
	}

	public void clickElementJS(WebElement ele, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public void scrollWindow(int x, int y) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(" + x + "," + y + ")");

	}

	public void scrollWindow(WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", ele);

	}

	public Boolean selectDropdownValue(WebElement ele, String dropdownvalue) {
		Boolean isdropdownvaluepresent = false;
		try {
			webdriverWaitVisibilityOf(ele, Config.webdriverWait);
			selectDropdownByVisibleText(ele, dropdownvalue);
			isdropdownvaluepresent = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			isdropdownvaluepresent = false;
		}
		return isdropdownvaluepresent;

	}

	public void selectDropdownIndex(WebElement ele, int index) {
		webdriverWaitVisibilityOf(ele, Config.webdriverWait);

		selectDropdownByIndex(ele, index);
	}

	public void clear(WebElement ele) {
		webdriverWaitClickable(ele, Config.webdriverWait);
		ele.clear();

	}

	public boolean isElementPresent(String xpath) {
		boolean ispresent = false;
		for (int i = 0; i < Config.webdriverWait; i++) {

			int result = driver.findElements(By.xpath(xpath)).size();
			if (result > 0) {
				ispresent = true;
				break;
			}

			waitForXSeconds(1000);

		}
		return ispresent;
	}

	public Boolean isEnabled(WebElement ele) {

		boolean isenabled = false;

		try {
			waitForXSeconds(2000);
			isenabled = ele.isEnabled();

		} catch (Exception e) {
			isenabled = false;
		}

		return isenabled;
	}

	public boolean isDisplayed(WebElement ele) {
		Boolean isDisplayed = false;

		try {
			webdriverWaitVisibilityOf(ele, Config.webdriverWait);
			isDisplayed = isElementDisplayed(ele);

		} catch (Exception e) {
			System.out.println(ele + " element is not displayed.");
			isDisplayed = false;
		}

		return isDisplayed;
	}

	public String getAttribute(WebElement ele, String attributeName) {

		String attrValue = null;
		if (ele.isDisplayed()) {
			attrValue = ele.getAttribute(attributeName);
		}
		return attrValue;

	}

	public void sendKeys(WebElement ele, int waitInSec, String text) {
		webdriverWaitVisibilityOf(ele, waitInSec);
		enterText(ele, text);

	}

	public void sendKeys(WebElement ele, int waitInSec, String text, boolean isWaitReq) {
		if (isWaitReq)
			webdriverWaitVisibilityOf(ele, waitInSec);
		enterText(ele, text);

	}

	public String getText(WebElement ele, int waitInSec) {
		webdriverWaitUntilText(ele, waitInSec);
		return getElementText(ele);
	}

	public String webDriverWaitGetValue(WebElement ele, int waitInSec, String text) {
		webdriverWaitTextToBePresent(ele, waitInSec, text);
		return getAttributeValue(ele);
	}

	public boolean isSelected(WebElement ele) {
		return ele.isSelected();
	}

	public String webDriverWaitGetText(WebElement ele, int waitInSec, String text) {
		webdriverWaitTextToBePresent(ele, waitInSec, text);
		return getElementText(ele);
	}

	public void webDriverWaitAndClick(WebElement ele, int waitInSec) {

		webdriverWaitClickable(ele, waitInSec);
		clickElement(ele);
	}

	public Boolean selectDropdownValue(WebElement ele, int waitInSec, String dropdownvalue) {
		Boolean isdropdownvaluepresent = false;
		try {
			webdriverWaitVisibilityOf(ele, waitInSec);
			selectDropdownByVisibleText(ele, dropdownvalue);
			isdropdownvaluepresent = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			isdropdownvaluepresent = false;
		}
		return isdropdownvaluepresent;

	}

	public String selectByVisibleTextAndReturnValue(WebElement ele, int waitInSec, String dropdownvalue) {
		String value = null;
		try {
			webdriverWaitVisibilityOf(ele, waitInSec);
			Select oSelect = new Select(ele);
			oSelect.selectByVisibleText(dropdownvalue);
			WebElement option = oSelect.getFirstSelectedOption();
			value = option.getAttribute("value");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return value;

	}

	public void selectDropdownIndex(WebElement ele, int waitInSec, int index) {
		webdriverWaitVisibilityOf(ele, waitInSec);

		selectDropdownByIndex(ele, index);
	}

	public void clear(WebElement ele, int waitInSec) {
		webdriverWaitClickable(ele, waitInSec);
		ele.clear();

	}

	public Boolean isDisplayed(WebElement ele, int waitInSec) {
		Boolean isDisplayed = false;

		try {
			webdriverWaitVisibilityOf(ele, waitInSec);
			isDisplayed = isElementDisplayed(ele);

		} catch (Exception e) {
			isDisplayed = false;
		}

		return isDisplayed;
	}

	public void performMouseHover(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
	}

	private boolean isElementDisplayed(WebElement ele) {

		return ele.isDisplayed();
	}

	private void webdriverWaitVisibilityOf(WebElement ele, int waitInSec) {
		new WebDriverWait(driver, waitInSec).until(ExpectedConditions.visibilityOf(ele));
	}

	private void webdriverWaitUntilText(WebElement ele, int waitInSec) {
		try {
			new WebDriverWait(driver, waitInSec).until(
					(ExpectedCondition<Boolean>) driver -> (ele.getText().length() != 0) && (ele.getText() != null));
		} catch (Throwable e) {
		}
	}

	private void webdriverWaitClickable(WebElement ele, int waitInSec) {
		new WebDriverWait(driver, waitInSec).until(ExpectedConditions.elementToBeClickable(ele));
	}

	private void webdriverWaitTextToBePresent(WebElement ele, int waitInSec, String text) {
		new WebDriverWait(driver, waitInSec).until(ExpectedConditions.textToBePresentInElementValue(ele, text));
	}

	private void enterText(WebElement ele, String text) {
		ele.sendKeys(text);
	}

	private String getElementText(WebElement ele) {
		String elementtext = "";
		try {
			elementtext = ele.getText();
		} catch (Exception e) {
			elementtext = e.getMessage();
		}

		return elementtext;
	}

	private String getAttributeValue(WebElement ele) {
		String elementtext = "";
		try {
			elementtext = ele.getAttribute("value");
		} catch (Exception e) {
			elementtext = e.getMessage();
		}

		return elementtext;
	}

	private void selectDropdownByVisibleText(WebElement ele, String dropdownvalue) {
		Select oSelect = new Select(ele);
		oSelect.selectByVisibleText(dropdownvalue);
	}

	private void selectDropdownByIndex(WebElement ele, int index) {
		Select oSelect = new Select(ele);
		oSelect.selectByIndex(index);
	}
}
