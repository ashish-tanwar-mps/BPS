package stepDefinitions;

import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import pageObjects.searchObj;
import testRailManager.TestRailManager;
import utility.CommonFunctions;
import utility.PropertyFile;
import utility.WebDriverController;

public class searchStepDef {


	WebDriverController maindriver = new WebDriverController();
	WebDriver driver = maindriver.rdriver();
	//	WebDriver driver = maindriver.initialize_webdriver();
	searchObj spo = new searchObj(driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	TestRailManager testrail = new TestRailManager();
	static Logger log = Logger.getLogger(searchStepDef.class);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	PropertyFile propp = new PropertyFile();
	Properties p = propp.propertyf();
	CommonFunctions cmf= new CommonFunctions();

	//	Step Definitions

	@Then("user enter search term {string}")
	public void user_enter_search_term(String searchterm) {

		try {
			wait.until(ExpectedConditions.visibilityOf(spo.searchInputBox));
			wait.until(ExpectedConditions.elementToBeClickable(spo.searchInputBox));
			spo.enter_search_term(searchterm);
			testrail.addResultsToTestrail("Pass", "448667", "Search term entered successfully");
		}catch(Exception e) {
			log.info("------------------FAILED : Due to exception---------------------" + e);
			testrail.addResultsToTestrail("Fail", "448667", "Failed : Due to exception : "+e);
		}
	}

	@Then("user click on search button")
	public void user_click_on_search_button() {

		try {
			wait.until(ExpectedConditions.visibilityOf(spo.submitSearchBtn));
			wait.until(ExpectedConditions.elementToBeClickable(spo.submitSearchBtn));
			spo.click_submit_search_image();
			testrail.addResultsToTestrail("Pass", "448668", "Search button clicked successfully");
		}catch(Exception e) {
			log.info("------------------FAILED : Due to exception---------------------" + e);
			testrail.addResultsToTestrail("Fail", "448668", "Failed : Due to exception : "+e);
		}
	}

	@Then("verify search result title with search term {string}")
	public void verify_search_result_title_with_search_term(String searchterm) {

		wait.until(ExpectedConditions.visibilityOf(spo.searchResultTitle));
		String actualText = spo.get_search_result_title();
		log.info("Expected Search result title is : "+searchterm);
		log.info("Actual Search result title is : "+ actualText);

		if(actualText.contains(searchterm)) {
			System.out.println("Search result verified");
			log.info("Search result verified");
			testrail.addResultsToTestrail("Pass","448661", "Search result verified");
		}else {
			System.out.println("Search result not verified");
			log.info("Search result not verified");
			testrail.addResultsToTestrail("Fail","448661", "Search result not verified");
		}

	}

}
