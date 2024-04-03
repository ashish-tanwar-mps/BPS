package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import utility.CommonFunctions;

public class loginObj {

	WebDriver driver ;
	static Logger log = Logger.getLogger(loginObj.class);
	CommonFunctions cmf= new CommonFunctions();

	public loginObj(WebDriver driver )
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	//	Web Elements

	@FindBy(xpath="//section[@id='block-sitebranding2svg']")
	public WebElement headerLogo;

	@FindBy(xpath="//a[@class='dropdown-toggle user__icon']")
	public WebElement loginBtn;
	
	@FindBy(xpath="//h1")
	public WebElement loginSSOTitle;
	
	@FindBy(xpath="//input[@id='username']")
	public WebElement userName;
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement passWord;
	
	@FindBy(xpath="//input[@class='button']")
	public WebElement signInSSOBtn;

	//	WebElement Functions
	
	public void click_sign_in_button_on_SSO_page() {
		signInSSOBtn.click();
		log.info("Sign in button SSO page clicked");
	}
	
	public void enter_password(String password) {
		passWord.sendKeys(password);
		log.info("Password entered");
	}
	
	public void enter_username(String username)
	{
		userName.sendKeys(username);
		log.info("Username entered");
	}
	
	public String get_sso_title() {
		String text = loginSSOTitle.getText();
		return text;
	}

	public void click_login_button() {
		loginBtn.click();
		log.info("Log In button clicked");
	}

}
