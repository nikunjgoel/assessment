package helper;

public class Constants {
	
	/**
	 * @author nikgoel1
	 */

	public static final String userName_Id = "email";
	public static final String password_Id = "password";
	public static final String loginsubmit_Id = "loginForm";
	public static final String LoginButton_Id = "//input[@id='desktopSearch']/following::div[4]/div//ul/li/a";
	public static final String allowCookie_Id = "ensCloseBanner";
	public static final String searchbox_xpath = "//input[@id='desktopSearch']";
	public static final String searchbutton_xpath = "(//button[@data-test='searchForm-submit'])[2]";// (//*[@name='Search'])[1]";
	public static final String createDealLink_xpath = "//span[contains(text(),'Create Deal')]";
	public static final String logOutlLink_xpath = "//span[contains(text(),'Log Out')]";
	public static final String result_xpath = "//div[@data-chunk='1']/section[1]";
	public static final String checklist_xpath = "//span[contains(text(),'Add to wish list')]/parent::div/parent::button";
	public static final String basket_xpath = "(//a[@href='/basket'])[2]";
	public static final String addtobasket_xpath = "(//div[@id='add-to-basket']//button)[1]";
	public static final String size_xpath = "//input[@value='M']";
	public static final String checkout_xpath = "//a[@id='link-button--basket-continue-securely']";
	public static final String guestradio_xpath = "(//input[@name='guest'])[2]";
	public static final String emailguests_xpath = "//input[@name='email']";
	public static final String continueGuest_xpath = "//button[@data-test='loginForm-submit']";
	public static final String delievery_xpath = "//button[@data-id='delivery']";
	public static final String titledelivery_xpath = "//input[@id='title']";
	public static final String firstnamedel_xpath = "//input[@id='firstName']";
	public static final String lastnamedel_xpath = "//input[@title='lastName']";
	public static final String phonedelivery_xpath = "//input[@id='phoneNumber-number']";
	public static final String addressdel_xpath = "//input[@id='address-search-input']";
	public static final String addressbutton_xpath = "//div[@data-test='address-search-input-results-container']/ul/li[2]/button";
	public static final String useaddressDelivery_xpath = "//button[@data-test='delivery-address-use-address']";
	public static final String deliverynext_xpath = "//input[@id='deliveryMethod-5:5']";
	public static final String seconddaydate_xpath = "(//ul[@data-test='day-picker-grid-container']//ul/li[7]/following::li/button[not(contains(@class, 'disabled'))])[3]";

	public static final String continuebuttonpayment_xpath = "//span[contains(text(),'Continue to payment')]/parent::button";
	public static final String messagedeliverydateSuccess_xpath = "//h2[@data-test='message-title']";
	public static final String reviewPageMessage_xpath = "//p[@data-test='delivery-details-message']";
	public static final String delieverPageMessagetime_xpath = "//p[@data-test='day-picker-additional-information']";

	public static final String payment_xpath = "//button[@data-test='payment-submit-button']";
	public static final String paypal_xpath = "//input[@id='paymentOption:payPal']";
	public static final String paypalbutton_xpath = "//button[@data-test='paypal-button']";

}
