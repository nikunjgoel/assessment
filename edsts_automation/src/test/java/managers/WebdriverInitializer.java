package managers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Config;
import helper.ExtentManager;
import helper.GenUtility;
import pageClasses.LoginPage;
import stepDefinition.Hooks;

public class WebdriverInitializer {

	public WebDriver driver = null;
	public DesiredCapabilities caps = null;
	public LoginPage _loginPage = null;
	// public MenuPage _menuPage = null;
	// public XlsReader _testdatapath = new XlsReader(Config.testDataFilePath);

	public ExtentReports _extent = ExtentManager.getInstance();

	public ExtentTest _test;
	
	/**
	 * @author nikgoel1
	 * @param testcaseName
	 * @param browser
	 * @return
	 */
	public WebDriver init_driver(String testcaseName,String browser) {

		try {

			_test = _extent.startTest(testcaseName + " test case Started!!");
			caps = new DesiredCapabilities();


			switch (browser) {

			
			case "chrome":
				ChromeOptions options = new ChromeOptions(); 
				caps = DesiredCapabilities.chrome();
				caps.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
     			caps.setCapability(ChromeOptions.CAPABILITY, options);
				System.setProperty("webdriver.chrome.driver", Config.chromeDriverExePath);
				//if(testdata)
				driver = new ChromeDriver(caps);
				break;
				}
			
			driver.manage().timeouts().implicitlyWait(Config.implicitWait, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			_test.log(LogStatus.PASS, "Browser opened successfully");
			
		} catch (Throwable e) {
			_test.log(LogStatus.FAIL, "Application URL is not browsed successfully");
			_test.log(LogStatus.FAIL, "SCreenshot below: "
					+ _test.addScreenCapture(GenUtility.takeScreenshot(driver, "ApplicationURLBrowse")));
			e.printStackTrace();
		}
		return driver;
	}
	
}
