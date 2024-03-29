package stepDefinitions;

import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.loginObj;
import testRailManager.TestRailManager;
import utility.CommonFunctions;
import utility.PropertyFile;
import utility.WebDriverController;

public class loginStepDef {

	WebDriverController maindriver = new WebDriverController();
	//	WebDriver driver = maindriver.rdriver();
	WebDriver driver = maindriver.initialize_webdriver();
	loginObj lpo = new loginObj(driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	TestRailManager testrail = new TestRailManager();
	static Logger log = Logger.getLogger(loginStepDef.class);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	PropertyFile propp = new PropertyFile();
	Properties p = propp.propertyf();
	CommonFunctions cmf= new CommonFunctions();

	//	Step Definitions

	@Given("user lands on BPS homepage")
	public void user_lands_on_BPS_homepage() {
		try {		
			wait.until(ExpectedConditions.visibilityOf(lpo.headerLogo));

			if(lpo.headerLogo.isDisplayed()) {
				System.out.println("User successfully land on homepage");
				log.info("User successfully land on homepage");
				//			testrail.addResultsToTestrail("Pass", "448450", "User successfully land on homepage");
			}else {
				System.out.println("Homepage landing unsuccessfull");
				log.info("Homepage landing unsuccessfull");
				//			testrail.addResultsToTestrail("Fail", "448450", "Homepage landing unsuccessfull");
			}
		}catch(Exception e) {
			log.info("------------------FAILED : Due to exception---------------------" + e);
			//			testrail.addResultsToTestrail("Fail", "448450", "Failed : Due to exception : "+e);
		}
	}

	@Then("user click and verify Sign In button with {string}")
	public void user_click_and_verify_Sign_In_button_with(String title) {

		try {
			wait.until(ExpectedConditions.visibilityOf(lpo.signInBtn));
			wait.until(ExpectedConditions.elementToBeClickable(lpo.signInBtn));
			lpo.click_Sign_In_button();

			wait.until(ExpectedConditions.visibilityOf(lpo.loginSSOTitle));
			String actualTitle = lpo.get_sso_title();
			log.info("Actual login SSO title is : "+actualTitle);
			log.info("Expected login SSO title is : "+title);

			if(actualTitle.equals(title)) {
				System.out.println("Sign In button verified");
				log.info("Sign In button verified");
				//			testrail.addResultsToTestrail("Pass", "448451", "Sign In button verified");
			}else {
				System.out.println("Sign In button not verified");
				log.info("Sign In button not verified");
				//			testrail.addResultsToTestrail("Fail", "448451", "Sign In button not verified");
			}
		}catch(Exception e) {
			log.info("------------------FAILED : Due to exception---------------------" + e);
			//			testrail.addResultsToTestrail("Fail", "448450", "Failed : Due to exception : "+e);
		}
	}

	@Then("user enters username {string}")
	public void user_enters_username(String username) {

		try {
			wait.until(ExpectedConditions.visibilityOf(lpo.userName));
			wait.until(ExpectedConditions.elementToBeClickable(lpo.userName));
			lpo.enter_username(username);
		}catch(Exception e) {
			log.info("------------------FAILED : Due to exception---------------------" + e);
			//			testrail.addResultsToTestrail("Fail", "448450", "Failed : Due to exception : "+e);
		}
	}

	@Then("user enters password {string}")
	public void user_enters_password(String password) {

		try {
			wait.until(ExpectedConditions.visibilityOf(lpo.passWord));
			wait.until(ExpectedConditions.elementToBeClickable(lpo.passWord));
			lpo.enter_password(password);
		}catch(Exception e) {
			log.info("------------------FAILED : Due to exception---------------------" + e);
			//			testrail.addResultsToTestrail("Fail", "448450", "Failed : Due to exception : "+e);
		}

	}

	@Then("user click and verify Sign In button with valid credentials")
	public void user_click_and_verify_sign_in_button_with_valid_credentials() {

		wait.until(ExpectedConditions.visibilityOf(lpo.signInSSOBtn));
		wait.until(ExpectedConditions.elementToBeClickable(lpo.signInSSOBtn));
		lpo.click_sign_in_button_on_SSO_page();
				
	}

}





















   
