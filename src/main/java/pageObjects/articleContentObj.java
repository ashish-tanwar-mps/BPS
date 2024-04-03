package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.CommonFunctions;

public class articleContentObj {
	
	WebDriver driver ;
	static Logger log = Logger.getLogger(articleContentObj.class);
	CommonFunctions cmf= new CommonFunctions();

	public articleContentObj(WebDriver driver )
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	//	Web Elements
	
	@FindBy(xpath="//h1[@class='article__title']")
	public WebElement articleTitle;
	
	@FindBy(xpath="//h6[@class='article__fullname']")
	public WebElement authorName;
	
	@FindBy(xpath="//div[@class='journal-doi-info']//a")
	public WebElement articleDOI;
	
//	WebElement Functions
	
	public String get_article_doi_link() {
		String text = articleDOI.getText();
		return text;
	}
	
	public String get_author_name() {
		String name = authorName.getText();
		return name;
	}
	
	public String get_article_title_text() {
		String text = articleTitle.getText();
		return text;
	}

}
