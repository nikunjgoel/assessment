package pageClasses;

import java.awt.Menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Config;
import helper.Constants;
import helper.GenUtility;
import helper.TestBase;


public class LoginPage extends TestBase {

	
	WebDriver driver;
	ExtentTest test;
	public WebDriverWait wait;
	

	public LoginPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver=driver;
		this.test = test;
		wait = new WebDriverWait(driver, Config.webdriverWait);
		PageFactory.initElements(driver, this);
				
	}

	@FindBy(how = How.ID, using = Constants.userName_Id)
	public WebElement username;
	@FindBy(how = How.ID, using = Constants.password_Id)
	public WebElement password;
	@FindBy(how = How.ID, using = Constants.loginsubmit_Id)
	public WebElement login;
    @FindBy(how = How.XPATH, using = Constants.LoginButton_Id)
    public WebElement LoginButton;

    @FindBy(how = How.ID, using = Constants.allowCookie_Id)
    public WebElement allowCookie;
    
	 /** Author: nikgoel1
	   * Login Page
	   * @param username, Password
	   * the command line arguments.
	   * @exception Any exception
	   * @return null value.
	   */
	public void login(String UserName, String Password) {
		try {
			System.out.println("Login page driver " + driver);
			test.log(LogStatus.INFO, "Entering user credentials on login page and hitting Login button");
			//clickElement(allowCookie);
			//clickElement(LoginButton);
			//clickElement(LoginButton);
			sendKeys(username, UserName);
			sendKeys(password, Password);
			webDriverWaitAndClick(login);
			test.log(LogStatus.PASS, "User "+UserName+" credentials on login page are entered and hit Login button successfully");
		} 
		
		catch (Exception e) {
			System.out.println(e.getMessage());
			
			test.log(LogStatus.FAIL, "There is something wrong while entering user credentials on login page: "+ e.getMessage());
			test.log(LogStatus.FAIL,
					"Screenshot below: " + test.addScreenCapture(GenUtility.takeScreenshot(driver, "LoginFormException")));
			_extent.endTest(test);
			_extent.flush();
			driver.close();
			Assert.fail("There is something wrong while entering user credentials on login page:"+ e.getMessage());
		}
	}

}
