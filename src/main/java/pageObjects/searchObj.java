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

	@FindBy(xpath="//input[@id='edit-submit-search']")
	public WebElement submitSearchBtn;
	
	@FindBy(xpath="//input[@id='edit-query']")
	public WebElement searchInputBox;
	
	@FindBy(xpath="//div[@class='col-12 col-medium-tablet-8 col-desktop-9']//h3")
	public WebElement searchResultTitle;

	//	WebElement Functions
	
	public String get_search_result_title() {
		String text = searchResultTitle.getText();
		return text;
	}
	
	public void enter_search_term(String searchterm) {
		searchInputBox.sendKeys(searchterm);
		log.info("Search term entered");
	}
	
	public void click_submit_search_image() {
		submitSearchBtn.click();
		log.info("Search button clicked");
	}

}
