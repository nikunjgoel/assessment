package cucumber;

import org.openqa.selenium.WebDriver;
import managers.PageObjectManager;

public class TestContext {
	public WebDriver driver;
	public PageObjectManager pageObjectManager;
	public ScenarioContext scenarioContext;
	

	public TestContext() {
		scenarioContext = new ScenarioContext();
	}


	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
}
