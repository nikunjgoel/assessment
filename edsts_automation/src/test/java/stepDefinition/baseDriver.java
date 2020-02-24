package stepDefinition;

import org.testng.annotations.Test;

import managers.PageObjectManager;
import managers.WebdriverInitializer;

public class baseDriver {
	static PageObjectManager pageObjectManager;
	@Test
	public void BaseDriver(String testCaseName) {
		/* if(testBobj == null ) */
		WebdriverInitializer driverMgr = new WebdriverInitializer();
		pageObjectManager = new PageObjectManager(driverMgr.init_driver(testCaseName, "chrome"), driverMgr._test);

	}
}
