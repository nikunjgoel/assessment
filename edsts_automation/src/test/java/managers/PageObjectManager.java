package managers;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import pageClasses.PaymentPage;
import pageClasses.RestAssurePage;
import pageClasses.ReviewPage;
import pageClasses.HomePage;
import pageClasses.LoginPage;
import pageClasses.ResultPage;
import pageClasses.SearchPage;
import pageClasses.CheckoutPage;
import pageClasses.DelieveryPage;

public class PageObjectManager {

	private WebDriver driver;

	private ExtentTest _test;

	private LoginPage loginPage;

	private HomePage homePage;

	private SearchPage searchPage;

	private ResultPage resultPage;

	private ReviewPage reviewPage;

	private CheckoutPage checkOutPage;
	private DelieveryPage delieveryPage;
	
	private RestAssurePage restAssurePage;
	

	public PageObjectManager(WebDriver driver, ExtentTest _test) {

		this.driver = driver;
		this._test = _test;

	}

	public LoginPage getLoginPage() {

		return (loginPage == null) ? loginPage = new LoginPage(driver, _test) : loginPage;

	}

	public HomePage getHomePage() {

		return (homePage == null) ? homePage = new HomePage(driver, _test) : homePage;

	}

	public SearchPage getSearchPage() {

		return (searchPage == null) ? searchPage = new SearchPage(driver, _test) : searchPage;
	}

	public ResultPage getResultPage() {
		return (resultPage == null) ? resultPage = new ResultPage(driver, _test) : resultPage;
	}

	public CheckoutPage getCheckOutPage() {
		return (checkOutPage == null) ? checkOutPage = new CheckoutPage(driver, _test) : checkOutPage;
	}
	
	public ReviewPage getReviewPage() {
		return (reviewPage == null) ? reviewPage = new ReviewPage(driver, _test) : reviewPage;
	}
	public DelieveryPage getDelieveryPage() {
		return (delieveryPage == null) ? delieveryPage = new DelieveryPage(driver, _test) : delieveryPage;
	}
	
	public RestAssurePage getRestAssurePage() {
		return (restAssurePage == null) ? restAssurePage = new RestAssurePage(driver, _test) : restAssurePage;
	}
}
