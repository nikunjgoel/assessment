package helper;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	public static ExtentReports _extent;

	public static ExtentReports getInstance() {

		if (_extent == null) {
                    System.out.println("ConfigPAth "+ Config.reportPath);
                    System.out.println("Configreport "+ Config.reportConfigFilePath);
			
			_extent = new ExtentReports(Config.reportPath, true);
			_extent.loadConfig(new File(Config.reportConfigFilePath));

			_extent.addSystemInfo("Selenium Version", "2.52.0").addSystemInfo("Cucumber Version", "1.2.5") 
					.addSystemInfo("Environment", Config.Environment)
					.addSystemInfo("Host Name", "EDTeam");
		}

		return _extent;
	}

}
