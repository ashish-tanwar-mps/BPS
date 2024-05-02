package stepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import pageObjects.articleContentObj;
import pageObjects.searchObj;
import testRailManager.TestRailManager;
import utility.CommonFunctions;
import utility.PropertyFile;
import utility.WebDriverController;

public class articleContentStepDef {


	static WebDriverController maindriver = new WebDriverController();
	static WebDriver driver = maindriver.rdriver();
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
	String actualTitleName;

	//	Step Definitions

	@Then("user accept the cookies")
	public void user_accept_the_cookies() throws InterruptedException {

		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(acpo.cookieContainer));
		driver.findElement(By.xpath("//mon-cb-main[@class='mon-cb-icon-shape-circle']")).getShadowRoot()
		.findElement(By.cssSelector(".body"))
		.findElement(By.cssSelector("mon-cb-home")).getShadowRoot()
		.findElement(By.cssSelector(".mon-cb-home"))
		.findElement(By.cssSelector("mon-cb-button[e2e-tag='acceptAllCookiesButton']")).click();
		System.out.println("Cookies Accepted");
		log.info("Cookies Accepted");

	}

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
			testrail.addResultsToTestrail("Pass", "448662", "Searched article clicked successfully");
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448662", "Failed : Due to exception : "+ e);
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
				testrail.addResultsToTestrail("Pass", "448663", "Article titles matched");
			}else {
				System.out.println("Article titles not matched");
				log.info("Article titles not matched");
				testrail.addResultsToTestrail("Fail", "448663", "Article titles not matched");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448663", "Failed : Due to exception : "+ e);
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
				testrail.addResultsToTestrail("Pass", "448669", "Author name matched");
			}else {
				System.out.println("Author name not matched");
				log.info("Author name not matched");
				testrail.addResultsToTestrail("Fail", "448669", "Author name not matched");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448669", "Failed : Due to exception : "+ e);
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
				testrail.addResultsToTestrail("Pass", "448670", "DOI Link matched");
			}else {
				System.out.println("DOI Link not matched");
				log.info("DOI Link not matched");
				testrail.addResultsToTestrail("Fail", "448670", "DOI Link not matched");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448670", "Failed : Due to exception : "+ e);
		}
	}

	@Then("verify Add to Favourites action tool bar {string}")
	public void verify_Add_to_Favourites_action_tool_bar (String searchterm) {

		try {
			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
			log.info("Page scrolled");

			wait.until(ExpectedConditions.visibilityOf(acpo.addToFavouritesBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.addToFavouritesBtn));
			acpo.click_add_to_favourites_button();

			//			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.favouriteBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.favouriteBtn));
			String actualText = acpo.get_favourite_button_text();
			log.info("favourite button text is : "+actualText);

			if(actualText.contains("Favourite")) {
				System.out.println("Add to Favourite button Verified");
				log.info("Add to Favourite button Verified");
				testrail.addResultsToTestrail("Pass", "448671", "Add to Favourite button Verified");
			}else {
				System.out.println("Add to Favourite button not Verified");
				log.info("Add to Favourite button not Verified");
				testrail.addResultsToTestrail("Fail", "448671", "Add to Favourite button not Verified");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448671", "Failed : Due to exception : "+ e);
		}
	}

	@Then("verify manage your favourites section {string}")
	public void verify_manage_your_favourites_section (String searchterm){
		try {
			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
			log.info("Page scrolled");

			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.favouriteBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.favouriteBtn));
			acpo.click_favourite_button();

			wait.until(ExpectedConditions.visibilityOf(acpo.manageFavouriteBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.manageFavouriteBtn));
			acpo.click_manage_favourite_button();

			wait.until(ExpectedConditions.visibilityOf(acpo.manageFavouriteTitle));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.manageFavouriteTitle));
			String actualTitle = acpo.get_manage_favourite_title_text();
			log.info("Actual manage Favourite Title is : "+actualTitle);

			if(actualTitle.equals("Manage your favourites")) {
				System.out.println("Manage your favourite section Verified");
				log.info("Manage your favourite section Verified");
				testrail.addResultsToTestrail("Pass", "448672", "Manage your favourite section Verified");
			}else {
				System.out.println("Manage your favourite section not Verified");
				log.info("Manage your favourite section not Verified");
				testrail.addResultsToTestrail("Fail", "448672", "Manage your favourite section not Verified");
			}
			driver.navigate().back();
			log.info("Back button pressed");
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448672", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify remove from favourites section {string}")
	public void verify_remove_from_favourites_section (String searchterm) {

		try {
			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
			log.info("Page scrolled");

			wait.until(ExpectedConditions.visibilityOf(acpo.favouriteBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.favouriteBtn));
			acpo.click_favourite_button();

			wait.until(ExpectedConditions.visibilityOf(acpo.removeFavouriteBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.removeFavouriteBtn));
			acpo.click_remove_favourite_button();

			wait.until(ExpectedConditions.visibilityOf(acpo.addToFavouritesBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.addToFavouritesBtn));
			if(acpo.addToFavouritesBtn.isDisplayed()) {
				System.out.println("Remove from favourite section Verified");
				log.info("Remove from favourite section Verified");
				testrail.addResultsToTestrail("Pass", "448673", "Remove from favourite section Verified");
			}else {
				System.out.println("Remove from favourite section not Verified");
				log.info("Remove from favourite section not Verified");
				testrail.addResultsToTestrail("Fail", "448673", "Remove from favourite section not Verified");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448673", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify cite button {string}")
	public void verify_cite_button (String searchterm) throws InterruptedException {

		try {
			wait.until(ExpectedConditions.visibilityOf(acpo.citeBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.citeBtn));
			acpo.click_cite_button();

			wait.until(ExpectedConditions.visibilityOf(acpo.citeLabel));
			String actualTitle= acpo.get_cite_label_text();
			log.info("Cite label text is : "+actualTitle);

			if(actualTitle.equals("Cite")) {
				System.out.println("Cite action toolbar Verified");
				log.info("Cite action toolbar Verified");
				testrail.addResultsToTestrail("Pass", "448674", "Cite action toolbar Verified");
			}else {
				System.out.println("Cite action toolbar not Verified");
				log.info("Cite action toolbar not Verified");
				testrail.addResultsToTestrail("Fail", "448674", "Cite action toolbar not Verified");
			}
			//          Printing the list of Items in Cite section
			cmf.user_get_link_and_there_title_of_page(acpo.citeItems);

			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.citeCloseBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.citeCloseBtn));
			acpo.click_cite_modal_close_button();

		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448674", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify share button")
	public void verify_share_button() throws InterruptedException {

		try {
			Thread.sleep(2000);
			//			
			//			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
			//			log.info("Page scrolled");

			wait.until(ExpectedConditions.visibilityOf(acpo.shareBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.shareBtn));
			acpo.click_share_button();

//			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.shareCloseBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.shareCloseBtn));
			String actualTitle= acpo.get_share_title_text();
			log.info("Actual Title is : "+ actualTitle);

			if(actualTitle.equals("Share")) {
				System.out.println("Share action toolbar Verified");
				log.info("Share action toolbar Verified");
				testrail.addResultsToTestrail("Pass", "448675", "Share action toolbar Verified");
			}else {
				System.out.println("Share action toolbar not Verified");
				log.info("Share action toolbar not Verified");
				testrail.addResultsToTestrail("Fail", "448675", "Share action toolbar not Verified");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448675", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify share social icon {string}{string}")
	public void verify_share_social_icon (String social,String testcaseid) throws InterruptedException {

		try {
			String xpath = cmf.create_Xpath("shareSocialPre", social, "postPart");
			log.info("Xpath is : "+xpath);

			Thread.sleep(2000);
			elm = driver.findElement(By.xpath(xpath));
			wait.until(ExpectedConditions.visibilityOf(elm));
			wait.until(ExpectedConditions.elementToBeClickable(elm));
			String actualTitle = elm.getAttribute("href");
			log.info("Actual Title is :"+actualTitle);
			log.info("Expected title is : "+social);

			if(actualTitle.contains(social)) {
				System.out.println("Share social media title for " + social + " verified");
				log.info("Share social media title for " + social + " verified");
				testrail.addResultsToTestrail("Pass", testcaseid, "Share social media title for " + social + " verified");
			}else {
				System.out.println("Share social media title for " + social +" not verified");
				log.info("Share social media title for " + social + " not verified");
				testrail.addResultsToTestrail("Fail",testcaseid, "Share social media title for " + social + " not verified");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", testcaseid, "Failed : Due to exception : "+ e);	
		}
	}

	@Then("close share modal")
	public void close_share_modal() {

		try {
			wait.until(ExpectedConditions.visibilityOf(acpo.shareCloseBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.shareCloseBtn));
			acpo.click_share_close_button();
			testrail.addResultsToTestrail("Pass", "448679", "Share modal closed successfully");
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448679", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify create alert functionality")
	public void verify_create_alert_functionality() throws InterruptedException {

		try {
			String alertBtnText = acpo.get_alert_button_text();
			log.info("Alert Button text on first run is : "+alertBtnText); 

			if(alertBtnText.equals("Alert ON")) {
				log.info("Alert already Exist");

				wait.until(ExpectedConditions.visibilityOf(acpo.alertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertBtn));
				acpo.click_alert_button();

				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(acpo.pauseAlertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.pauseAlertBtn));
				acpo.click_pause_alert_button();

				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(acpo.alertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertBtn));
				alertBtnText = acpo.get_alert_button_text();
				log.info("Alert Button text after alert is paused is : "+alertBtnText);

				Thread.sleep(2000);
				if(alertBtnText.equals("Alert OFF")) {
					System.out.println("Pause alert functionality verified");
					log.info("Pause alert functionality verified");
					testrail.addResultsToTestrail("Pass", "448680", "Pause alert functionality verified");
				}else {
					System.out.println("Pause alert functionality not verified");
					log.info("Pause alert functionality not verified");
					testrail.addResultsToTestrail("Fail","448680", "Pause alert functionality not verified");
				}

				jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
				log.info("Page scrolled");
			}
			//			else if(alertBtnText.equals("Alert OFF")) {
			//				verify_manage_alert_functionality();
			//			}
			else {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(acpo.alertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertBtn));
				acpo.click_alert_button();

				//			Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOf(acpo.createAlertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.createAlertBtn));
				acpo.click_create_alert_button();

				//			Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOf(acpo.alertTitle));
				String actualTitle = acpo.get_alert_title();
				actualTitleName = acpo.get_alert_title_name();
				log.info("Actual alert title is : "+actualTitle);
				log.info("Actual alert title name is : "+actualTitleName);

				if(actualTitle.equals("Create alert")) {
					System.out.println("Create Alert button verified");
					log.info("Create Alert button verified");
					testrail.addResultsToTestrail("Pass", "448681", "Create Alert button verified");
				}else {
					System.out.println("Create Alert button not verified");
					log.info("Create Alert button not verified");
					testrail.addResultsToTestrail("Fail","448681", "Create Alert button not verified");
				}

				//			Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOf(acpo.alertEmailSlider));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertEmailSlider));
				acpo.click_alert_email_slider();
				log.info("Email notification for Alert : OFF");

				wait.until(ExpectedConditions.visibilityOf(acpo.alertEmailSlider));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertEmailSlider));
				acpo.click_alert_email_slider();
				log.info("Email notification for Alert : ON");

				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(acpo.saveAlertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.saveAlertBtn));
				acpo.click_save_alert_button();

				Thread.sleep(5000);
				wait.until(ExpectedConditions.visibilityOf(acpo.alertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertBtn));
				alertBtnText = acpo.get_alert_button_text();
				log.info("Alert Button text after a new alert is saved is : "+alertBtnText);

				if(alertBtnText.equals("Alert ON")) {
					System.out.println("Alert created successfully");
					log.info("Alert created successfully");
					testrail.addResultsToTestrail("Pass", "448682", "Alert created successfully");
				}else {
					System.out.println("Alert not created successfully");
					log.info("Alert not created successfully");
					testrail.addResultsToTestrail("Fail","448682", "Alert not created successfully");
				}

				jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
				log.info("Page scrolled");

				wait.until(ExpectedConditions.visibilityOf(acpo.alertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertBtn));
				acpo.click_alert_button();

				wait.until(ExpectedConditions.visibilityOf(acpo.pauseAlertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.pauseAlertBtn));
				acpo.click_pause_alert_button();

				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(acpo.alertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.alertBtn));
				alertBtnText = acpo.get_alert_button_text();
				log.info("Alert Button text after alert is paused is : "+alertBtnText);

				//			Thread.sleep(2000);
				if(alertBtnText.equals("Alert OFF")) {
					System.out.println("Pause alert functionality verified");
					log.info("Pause alert functionality verified");
					testrail.addResultsToTestrail("Pass", "448680", "Pause alert functionality verified");
				}else {
					System.out.println("Pause alert functionality not verified");
					log.info("Pause alert functionality not verified");
					testrail.addResultsToTestrail("Fail","448680", "Pause alert functionality not verified");
				}

				jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
				log.info("Page scrolled");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448681", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify manage alert functionality")
	public void verify_manage_alert_functionality() throws InterruptedException {

		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.alertBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.alertBtn));
			acpo.click_alert_button();

			wait.until(ExpectedConditions.visibilityOf(acpo.manageAlertBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.manageAlertBtn));
			acpo.click_manage_alert_button();

			Thread.sleep(2000);
			Select objSelect = new Select(acpo.alertSortDropdown);
			objSelect.selectByValue("created|desc");
			log.info("Newest first sorting selected");

			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(acpo.articleNameManageAlert));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.articleNameManageAlert));
			String articleName= acpo.get_article_name_on_manage_alert_page();
			log.info("Actual article name on manage alert page is : "+articleName);
			log.info("Expected article name on manage alert page is : "+actualTitleName);

			if(articleName.equals(actualTitleName)) {
				System.out.println("Article Names on Manage alert page matched");
				log.info("Article Names on Manage alert page matched");
				testrail.addResultsToTestrail("Pass", "448683", "Article Names on Manage alert page matched");

				wait.until(ExpectedConditions.visibilityOf(acpo.deleteAlertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.deleteAlertBtn));
				acpo.click_delete_alert_button();

				wait.until(ExpectedConditions.visibilityOf(acpo.modalDeleteAlertBtn));
				wait.until(ExpectedConditions.elementToBeClickable(acpo.modalDeleteAlertBtn));
				acpo.click_modal_delete_alert_button();

				driver.navigate().back();
				log.info("Back button pressed");

				driver.navigate().back();
				log.info("Back button pressed");
			}else {
				System.out.println("Article Names on Manage alert page not matched");
				log.info("Article Names on Manage alert page not matched");
				testrail.addResultsToTestrail("Fail","448683", "Article Names on Manage alert page not matched");

				driver.navigate().back();
				log.info("Back button pressed");
			}

		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448683", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify permissions button functionality {string}{string}")
	public void verify_permissions_button_functionality(String permissions,String copypermissionstext) throws InterruptedException {

		try {
			wait.until(ExpectedConditions.visibilityOf(acpo.permissionsBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.permissionsBtn));
			acpo.click_permission_button();

			List<String> tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			log.info("Tab switched");

			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.copyPermissionText));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.copyPermissionText));
			String actualText = acpo.get_copy_permisssion_text();
			log.info("Header title is : "+actualText);

			String actualTitle = driver.getTitle();
			log.info("Actual title is : "+actualTitle);

			String url = driver.getCurrentUrl();
			log.info("Current URL is : "+ url);

			//		Thread.sleep(2000);

			if(actualTitle.contains("Permissions")&& url.contains(permissions)
					//				&& actualText.equals(copypermissionstext) 
					) {
				System.out.println("Permissions functionality verified");
				log.info("Permissions functionality verified");
				testrail.addResultsToTestrail("Pass", "448684", "Permissions functionality verified");
			}else {
				System.out.println("Permissions functionality not verified");
				log.info("Permissions functionality not verified");
				testrail.addResultsToTestrail("Fail", "448684", "Permissions functionality not verified");
			}

			driver.close();
			driver.switchTo().window(tabs.get(0));
			log.info("Switched back to parent tab");

		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448684", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify focus view functionality {string}")
	public void verify_focus_view_functionality(String exitfocustext) throws InterruptedException {

		try {
			Thread.sleep(2000);

			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
			log.info("Page scrolled");

			wait.until(ExpectedConditions.visibilityOf(acpo.focusBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.focusBtn));
			acpo.click_focus_button();

			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.previousLink));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.previousLink));
			String actualText = acpo.get_exit_focus_button_text();
			log.info("Actual exit focus view button text is : "+actualText);
			log.info("Expected exit focus view text is : "+exitfocustext);

			try {
				acpo.actionToolsContainer.isDisplayed();
				log.info("Focus view button not verified");
			}catch(Exception e) {
				if(actualText.equals(exitfocustext)) {
					System.out.println("Focus view button verified");
					log.info("Focus view button verified");
					testrail.addResultsToTestrail("Pass", "448685", "Focus view button verified");
				}else {
					System.out.println("Focus view button not verified");
					log.info("Focus view button not verified");
					testrail.addResultsToTestrail("Fail", "448685", "Focus view button not verified");
				}
			}

			wait.until(ExpectedConditions.visibilityOf(acpo.focusBtn));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.focusBtn));
			acpo.click_focus_button();

			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.articleTitle));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.articleTitle));
			if(acpo.articleTitle.isDisplayed()) {
				System.out.println("Exit Focus view button verified");
				log.info("Exit Focus view button verified");
				testrail.addResultsToTestrail("Pass", "448686", "Exit Focus view button verified");
			}else {
				System.out.println("Exit Focus view button not verified");
				log.info("Exit Focus view button not verified");
				testrail.addResultsToTestrail("Fail", "448686", "Exit Focus view button not verified");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448685", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify next and previous link {string}{string}")
	public void verify_next_and_previous_link (String searchterm,String searchterm2) throws InterruptedException {

		try {
			Thread.sleep(2000);
			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.accessContentPane);
			log.info("Page scrolled");

			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(acpo.nextLink));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.nextLink));
			acpo.click_next_link();

			wait.until(ExpectedConditions.visibilityOf(acpo.articleTitle));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.articleTitle));
			String nextTitle = acpo.get_article_title_text();
			log.info("Article title in next page is : "+nextTitle);

			if(nextTitle.equals(searchterm2)) {
				System.out.println("Next link verified");
				log.info("Next link verified");
				testrail.addResultsToTestrail("Pass", "448687", "Next link verified");
			}else {
				System.out.println("Next link not verified");
				log.info("Next link not verified");
				testrail.addResultsToTestrail("Fail", "448687", "Next link not verified");
			}

			Thread.sleep(2000);

			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.accessContentPane);
			log.info("Page scrolled");

			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(acpo.previousLink));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.previousLink));
			acpo.click_previous_link();

			wait.until(ExpectedConditions.visibilityOf(acpo.articleTitle));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.articleTitle));
			String previousTitle= acpo.get_article_title_text();
			log.info("Article title on previous page is : "+previousTitle);

			if(previousTitle.equals(searchterm)) {
				System.out.println("Previous link verified");
				log.info("Previous link verified");
				testrail.addResultsToTestrail("Pass", "448688", "Previous link verified");
			}else {
				System.out.println("Previous link not verified");
				log.info("Previous link not verified");
				testrail.addResultsToTestrail("Fail", "448688", "Previous link not verified");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448689", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify Related content {string}{string}{string}{string}{string}")
	public void verify_Related_content(String rck1,String rck2,String rck3,String rck4,String rck5) throws InterruptedException {

		try {
			Thread.sleep(2000);
			//		jse.executeScript("arguments[0].scrollIntoView(true);",acpo.relatedContentText);
			//		log.info("Page scrolled");
			//		
			//		cmf.user_get_link_and_there_title_of_page(acpo.relatedContentMenu);

			log.info("Verifying element in the related content section");
			ArrayList<String> a1=new ArrayList<String>();
			ArrayList<String> a3=new ArrayList<String>();
			Collections.addAll(a1, rck2,rck1,rck3,rck4,rck5);
			Collections.addAll(a3, rck2,rck1,rck3,rck4,rck5);
			for(String res : a1) {
				log.info("a1: print :" + res);
			}
			ArrayList<String> a2=cmf.user_get_all_link_and_there_title(driver.findElements(By.xpath("//div[@class='clearfix']//a")));
			for (String resPdf : a2) {
				log.info("a2 print :" + resPdf);					
			}
			a3.removeAll(a2);
			a2.removeAll(a1);
			log.info("a3==="+a3);
			log.info("a2== "+a2);
			if(a2.isEmpty() && a3.isEmpty()) {
				System.out.println("Related content section verified");
				log.info("Related content section verified");
				testrail.addResultsToTestrail("Pass", "448690", "Related content section verified");
			}else {
				System.out.println("Related content section not verified");
				log.info("Related content section not verified");
				testrail.addResultsToTestrail("Fail", "448690", "Related content section not verified");
				log.info("a3==="+a3);
				log.info("a2== "+a2);					
			}	
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448690", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify keywords content {string}{string}{string}{string}{string}{string}{string}{string}{string}{string}")
	public void verify_keywords_content(String k1,String k2,String k3,String k4,String k5
			,String k6,String k7,String k8,String k9,String k10) throws InterruptedException{

		try {
			ArrayList<String> a1=new ArrayList<String>();
			ArrayList<String> a3=new ArrayList<String>();
			Collections.addAll(a1, k2,k1,k3,k4,k5,k6,k7,k8,k9,k10);
			Collections.addAll(a3, k2,k1,k3,k4,k5,k6,k7,k8,k9,k10);
			for(String res : a1) {
				log.info("a1: print :" + res);
			}
			ArrayList<String> a2=cmf.user_get_all_link_and_there_title(driver.findElements(By.xpath("//div[@class='sidebar-article']//div[@class='sidebar-article-wrapper']//ul//a")));
			for (String resPdf : a2) {
				log.info("a2 print :" + resPdf);					
			}
			a3.removeAll(a2);
			a2.removeAll(a1);
			log.info("a3==="+a3);
			log.info("a2== "+a2);
			if(a2.isEmpty() && a3.isEmpty()) {
				System.out.println("Keywords content verified");
				log.info("Keywords content verified");
				testrail.addResultsToTestrail("Pass", "448691", "Keywords content verified");
			}else {
				System.out.println("Keywords content not verified");
				log.info("Keywords content not verified");
				testrail.addResultsToTestrail("Fail", "448691", "Keywords content not verified");
				log.info("a3==="+a3);
				log.info("a2== "+a2);					
			}		
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448691", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify Table of content section {string}{string}{string}{string}{string}{string}{string}")
	public void verify_Table_of_content_section (String ktc1,String ktc2,String ktc3,String ktc4,String ktc5,String ktc6,String ktc7) throws InterruptedException {

		try {
			ArrayList<String> a1=new ArrayList<String>();
			ArrayList<String> a3=new ArrayList<String>();
			Collections.addAll(a1, ktc2,ktc1,ktc3,ktc4,ktc5,ktc6,ktc7);
			Collections.addAll(a3, ktc2,ktc1,ktc3,ktc4,ktc5,ktc6,ktc7);
			for(String res : a1) {
				log.info("a1: print :" + res);
			}
			ArrayList<String> a2=cmf.user_get_all_link_and_there_title(driver.findElements(By.xpath("//div[@class='sidebar-article sidebar-sticky']//a")));
			for (String resPdf : a2) {
				log.info("a2 print :" + resPdf);					
			}
			a3.removeAll(a2);
			a2.removeAll(a1);
			log.info("a3==="+a3);
			log.info("a2== "+a2);
			if(a2.isEmpty() && a3.isEmpty()) {
				System.out.println("Table of content section verified");
				log.info("Table of content section verified");
				testrail.addResultsToTestrail("Pass", "448692", "Table of content section verified");
			}else {
				System.out.println("Table of content section not verified");
				log.info("Table of content section not verified");
				testrail.addResultsToTestrail("Fail", "448692", "Table of content section not verified");
				log.info("a3==="+a3);
				log.info("a2== "+a2);					
			}			
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448692", "Failed : Due to exception : "+ e);	
		}
	}

	@Then("verify Access this content pane")
	public void verify_Access_this_content_pane() throws InterruptedException {

		try {
			jse.executeScript("arguments[0].scrollIntoView(true);",acpo.authorName);
			log.info("Page scrolled");

			wait.until(ExpectedConditions.visibilityOf(acpo.accessContentPane));
			wait.until(ExpectedConditions.elementToBeClickable(acpo.accessContentPane));
			acpo.click_access_content_pane();

			if(acpo.purchaseLinkBtn.isDisplayed()) {
				System.out.println("Access content pane verified");
				log.info("Access content pane verified");
				testrail.addResultsToTestrail("Pass", "448693", "Access content pane verified");
			}else {
				System.out.println("Access content pane not verified");
				log.info("Access content pane not verified");
				testrail.addResultsToTestrail("Fail", "448693", "Access content pane not verified");
			}
		}catch(Exception e) {
			log.info("Failed : Due to exception" + e);
			testrail.addResultsToTestrail("Fail", "448693", "Failed : Due to exception : "+ e);	
		}
	}
	
	@Then("close the driver")
	public void close_the_driver() {
		
		driver.close();
	    log.info("Driver closed");	
	}
}
