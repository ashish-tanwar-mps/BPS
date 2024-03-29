package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.CommonFunctions;

public class searchObj {

	WebDriver driver ;
	static Logger log = Logger.getLogger(searchObj.class);
	CommonFunctions cmf= new CommonFunctions();

	public searchObj(WebDriver driver )
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	//	Web Elements

	@FindBy(xpath="//img[@alt='Close Search']")
	public WebElement closeSearchImg;
	
	@FindBy(xpath="//input[@id='search-desktop']")
	public WebElement searchInputBox;

	//	WebElement Functions
	
	public void enter_search_term(String searchterm) {
		searchInputBox.sendKeys(searchterm);
		log.info("Search term entered");
	}
	
	public void click_close_search_image() {
		closeSearchImg.click();
		log.info("Close search Image clicked");
	}

}
