package helper;

public class Config {

	public static String chromeDriverExePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
	public static String ieDriverExePath = "";
	public static String browserDriver = "ChromeDriver";
	public static String browser = "Chrome"; // IE/Chrome/firefox
	public static String testingEnv = "Local"; // Grid
	public static int implicitWait = 10;
	public static int webdriverWait = 20;

	// Site url
	public static String Environment = "QA";
	public static String siteUrl = "https://www.johnlewis.com/";
	public static String siteUrlQA = "https://www.johnlewis.com/";

	// Files location
	public static String reportPath = System.getProperty("user.dir") + "//reports//" + GenUtility.getDayWithSeconds()
			+ ".html";
	public static String reportConfigFilePath = System.getProperty("user.dir") + "//extent-config.xml";
	public static String testDataFilePath = System.getProperty("user.dir") + "//src//test//java//data//TestData.xlsx";
	public static String documentationPath = System.getProperty("user.dir") + "//documentations//";
	public static String DownloadFolder = System.getProperty("user.dir") + "\\Downloads\\";

	// Screenshots location
	public static String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\";

	// Documents Location
	/* public static String DocumentPath = System.getProperty("user.dir"); */
	public static String excelWorkbookName = "TestCase";
	public static String excelWorksheetName = "CreateEntity";
	public static String excelClumnName = "Test Case Name";
}
