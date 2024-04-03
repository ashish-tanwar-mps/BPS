package stepDefinitions;

import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import pageObjects.articleContentObj;
import pageObjects.searchObj;
import testRailManager.TestRailManager;
import utility.CommonFunctions;
import utility.PropertyFile;
import utility.WebDriverController;

public class articleContentStepDef {


	WebDriverController maindriver = new WebDriverController();
	WebDriver driver = maindriver.rdriver();
	//	WebDriver driver = maindriver.initialize_webdriver();
	articleContentObj acpo = new articleContentObj(driver);
	searchObj spo = new searchObj(driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	TestRailManager testrail = new TestRailManager();
	static Logger log = Logger.getLogger(articleContentStepDef.class);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	PropertyFile propp = new PropertyFile();
	Properties p = propp.propertyf();
	CommonFunctions cmf= new CommonFunctions();
	WebElement elm;

	//	Step Definitions

	@Then("user click on searched article {string}")
	public void user_click_on_searched_article (String searchterm) throws InterruptedException {

		try {
			wait.until(ExpectedConditions.visibilityOf(spo.searchResultTitle));	
			String xpath = cmf.create_Xpath("articlePre", searchterm,"postPart");
			log.info(" Xpath is : "+ xpath);
			elm = driver.findElement(By.xpath(xpath));
			//		Compulsory wait
			Thread.sleep(2000);
			elm.click();
			log.info("Searched article clicked successfully");
			//			testrail.addResultsToTestrail("Pass", "448459", "Searched article clicked successfully");
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
//			testrail.addResultsToTestrail("Fail", "448459", "Failed : Due to exception : "+ e);
		}
	}

	@Then("verify article content title with search term {string}")
	public void verify_article_content_title_with_search_term (String searchterm) throws InterruptedException {

		try {
			wait.until(ExpectedConditions.visibilityOf(acpo.articleTitle));
			Thread.sleep(2000);
			String actualTitle = acpo.get_article_title_text();
			log.info("Actual article title is : " +actualTitle);
			log.info("Expected article title is :"+ searchterm);

			if(actualTitle.equals(searchterm)) {
				System.out.println("Article titles matched");
				log.info("Article titles matched");
				//				testrail.addResultsToTestrail("Pass", "448460", "Article titles matched");
			}else {
				System.out.println("Article titles not matched");
				log.info("Article titles not matched");
				//				testrail.addResultsToTestrail("Fail", "448460", "Article titles not matched");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
//			testrail.addResultsToTestrail("Fail", "448460", "Failed : Due to exception : "+ e);
		}
	}

	@Then("verify author {string}")
	public void verify_author (String authorname) {

		try {
			wait.until(ExpectedConditions.visibilityOf(acpo.authorName));
			String actualAuthorName = acpo.get_author_name();
			log.info("Actual Author name : "+actualAuthorName);
			log.info("Expected Author name is : "+ authorname);

			if(actualAuthorName.contains(authorname)) {
				System.out.println("Author name matched");
				log.info("Author name matched");
				//				testrail.addResultsToTestrail("Pass", "448461", "Author name matched");
			}else {
				System.out.println("Author name not matched");
				log.info("Author name not matched");
				//				testrail.addResultsToTestrail("Fail", "448461", "Author name not matched");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
//			testrail.addResultsToTestrail("Fail", "448461", "Failed : Due to exception : "+ e);
		}
	}
	
	@Then("verify article DOI link {string}")
	public void verify_article_DOI_link (String doilink) {

		try {
			wait.until(ExpectedConditions.visibilityOf(acpo.articleDOI));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.articleDOI));
			String actualDOILink = acpo.get_article_doi_link();
			log.info("Actual DOI link is : "+ actualDOILink);
			log.info("Expected DOI Link is : "+ doilink);

			if(actualDOILink.equals(doilink)) {
				System.out.println("DOI Link matched");
				log.info("DOI Link matched");
//				testrail.addResultsToTestrail("Pass", "448463", "DOI Link matched");
			}else {
				System.out.println("DOI Link not matched");
				log.info("DOI Link not matched");
//				testrail.addResultsToTestrail("Fail", "448463", "DOI Link not matched");
			}
//			Thread.sleep(2000);
//			
//			WebElement elm1 = driver.findElement(By.xpath("//mon-cb-main[@class='mon-cb-icon-shape-circle']"));
//			log.info("Main component : "+elm1);
//			SearchContext xyz = elm1.getShadowRoot();
//			
//			WebElement text = xyz.findElement(By.xpath("//div[@class='title']//h1"));
//			String textname = text.getText();
//			log.info("text name is : "+textname);
//			
//			WebElement elmAccept = xyz.findElement(By.xpath("//button[@class='mon-cb-button-el primary']"));
			
//			WebElement elm2 = getShadowRootElement(elmAccept);
//			elmAccept.click();
//			log.info("Cookies clicked");
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
//			testrail.addResultsToTestrail("Fail", "448463", "Failed : Due to exception : "+ e);
		}
	}

	private WebElement getShadowRootElement(WebElement elm1) {
		// TODO Auto-generated method stub
		return null;
	}

}
