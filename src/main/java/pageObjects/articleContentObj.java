package pageObjects;

import java.util.List;

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
	
	@FindBy(xpath="//section[@class='block block-highwire-personalization block-highwire-markers-sign-up-menu clearfix']//a")
	public WebElement addToFavouritesBtn;
	
	@FindBy(xpath="//section[@class='block block-highwire-personalization block-highwire-markers-sign-up-menu clearfix']//h3[@class='item-list__title']")
	public WebElement favouriteBtn;
	
	@FindBy(xpath="//ul[@class=' item-list__highwire_personalization__alerts_signup_links']//a[normalize-space()='Manage favourites...']")
	public WebElement manageFavouriteBtn;
	
	@FindBy(xpath="//ul[@class=' item-list__highwire_personalization__alerts_signup_links']//a[normalize-space()='Remove from favourites']")
	public WebElement removeFavouriteBtn;
	
	@FindBy(xpath="//div[@class='container']/h3")
	public WebElement manageFavouriteTitle;
	
	@FindBy(xpath="//a[@class='action-cite']")
	public WebElement citeBtn;
	
	@FindBy(xpath="//div[@id='bps-city-popup']//h4[@class='modal-title']")
	public WebElement citeLabel;
	
	@FindBy(xpath="//div[@id='bps-city-popup']//button[@class='btn     btn-outline-primary']")
	public WebElement citeCloseBtn;
	
	@FindBy(xpath="//ul[@class='links item-list__highwire_citation_export']/li")
	public List<WebElement> citeItems; 
	
	@FindBy(xpath="//a[@class='action-share']")
	public WebElement shareBtn;
	
	@FindBy(xpath="//div[@id='bps-share-popup']//h4[@class='modal-title']")
	public WebElement shareTitle;
	
	@FindBy(xpath="//a[@class='a2a_button_facebook']")
	public WebElement facebookShareBtn;
	
	@FindBy(xpath="//a[@class='a2a_button_twitter']")
	public WebElement twitterShareBtn;
	
	@FindBy(xpath="//a[@class='a2a_button_linkedin']")
	public WebElement linkedinShareBtn;
	
	@FindBy(xpath="//mon-cb-main[@class='mon-cb-icon-shape-circle']")
	public WebElement cookieContainer;
	
	@FindBy(xpath="//div[@id='bps-share-popup']//button[@class='btn     btn-outline-primary']")
	public WebElement shareCloseBtn;
	
	@FindBy(xpath="//section[@class='block block-highwire-personalization block-highwire-alerts-sign-up-menu clearfix']//h3[@class='item-list__title']")
	public WebElement alertBtn;
	
	@FindBy(xpath="//a[@class='highwire-modal-trigger']")
	public WebElement createAlertBtn;
	
	@FindBy(xpath="//a[@id='edit-title']")
	public WebElement alertTitleName;
	
	@FindBy(xpath="//span[@id='ui-id-1']")
	public WebElement alertTitle;
	
	@FindBy(xpath="//span[@class='highwire-checkbox-slider__switch']")
	public WebElement alertEmailSlider;
	
	@FindBy(xpath="//input[@id='edit-submit']")
	public WebElement saveAlertBtn;
	
	@FindBy(xpath="//a[@class='alert-toggle-status alert-enabled']")
	public WebElement pauseAlertBtn;
	
	@FindBy(xpath="//ul[@class=' item-list__highwire_personalization__alerts_signup_links']//a[normalize-space()='Manage alerts...']")
	public WebElement manageAlertBtn;
	
	@FindBy(xpath="//select[@id='edit-sort']")
	public WebElement alertSortDropdown;
	
	@FindBy(xpath="//a[@id='edit-title']")
	public WebElement articleNameManageAlert;
	
	@FindBy(xpath="(//a[@class='highwire-modal-trigger'])[1]")
	public WebElement deleteAlertBtn;
	
	@FindBy(xpath="//input[@id='edit-submit--2']")
	public WebElement modalDeleteAlertBtn;
	
	@FindBy(xpath="//ul[@class='highwire-personalization-manage-list highwire-personalization-manage-alerts-list item-list__highwire_personalization_manage_alerts_list']")
	public WebElement manageAlertContainer;
	
	@FindBy(xpath="//a[@class='action-permissions']")
	public WebElement permissionsBtn;
	
	@FindBy(xpath="(//div[@property='schema:text']//span[normalize-space()='Copies and permissions'])[1]")
	public WebElement copyPermissionText;
	
	@FindBy(xpath="(//a[@class='article__focus_link'])[1]")
	public WebElement focusBtn;
	
	@FindBy(xpath="(//a[@class='article__focus_link'])[1]//span[normalize-space()='Exit focus']")
	public WebElement exitFocusBtn;
	
	@FindBy(xpath="//a[@class='article__previous_link']")
	public WebElement previousLink;
	
	@FindBy(xpath="(//a[@class='article__next_link'])[1]")
	public WebElement nextLink;
	
	@FindBy(xpath="//div[@class='action_tools']")
	public WebElement actionToolsContainer;
	
	@FindBy(xpath="//button[@class='accordion-button collapsed article__accordian_wrapper']")
	public WebElement accessContentPane;
	
	@FindBy(xpath="//div[@class='block-region-bottom-right']//h5[normalize-space()='Related content']")
	public WebElement relatedContentText;
	
	@FindBy(xpath="//div[@class='sidebar-article-wrapper']")
	public List<WebElement> relatedContentMenu;
	
	@FindBy(xpath="//div[@class='sidebar-article']//h5[normalize-space()='Keywords']")
	public WebElement keywordsText;
	
	@FindBy(xpath="(//a[@class='highwire-ecommerce-add-to-cart highwire-ecommerce-purchase-link btn btn-primary'])[1]")
	public WebElement purchaseLinkBtn;

//	WebElement Functions
	
	public void click_access_content_pane() {
		accessContentPane.click();
		log.info("Access content pane clicked");
	}
	
	public String get_related_content_text() {
		String text = relatedContentText.getText();
		return text;
	}
	
	public void click_next_link() {
		nextLink.click();
		log.info("Next link clicked ");
	}
	
	public void click_previous_link() {
		previousLink.click();
		log.info("Previous link clicked");
	}
	
	public String get_exit_focus_button_text() {
		String text = exitFocusBtn.getText();
		return text;
	}
	
	public void click_focus_button() {
		focusBtn.click();
		log.info("Focus button clicked");
	}
	
	
	public String get_copy_permisssion_text(){
		String text = copyPermissionText.getText();
		return text;
	}
	
	public void click_permission_button() {
		permissionsBtn.click();
		log.info("Permissions button clicked");
	}
	
	public void click_modal_delete_alert_button() {
		modalDeleteAlertBtn.click();
		log.info("Delete alert button on delete modal clicked");
	}
	
	public void click_delete_alert_button() {
		deleteAlertBtn.click();
		log.info("Delete alert button clicked");
	}
	
	public String get_article_name_on_manage_alert_page() {
		String name = articleNameManageAlert.getText();
		return name;
	}
	
	public void click_manage_alert_button() {
		manageAlertBtn.click();
		log.info("Manage alert button clicked");
	}
	
	public void click_pause_alert_button() {
		pauseAlertBtn.click();
		log.info("Pause alert button clicked");
	}
	
	public void click_save_alert_button() {
		saveAlertBtn.click();
		log.info("Save alert button clicked");
	}
	
	public void click_alert_email_slider() {
		alertEmailSlider.click();
		log.info("Alert email slider clicked");
	}
	
	public String get_alert_title() {
		String title = alertTitle.getText();
		return title;
	}
	
	public String get_alert_title_name() {
		String title = alertTitleName.getText();
		return title;
	}
	
	public void click_create_alert_button() {
		createAlertBtn.click();
		log.info("Create alert button clicked");
	}
	
	public String get_alert_button_text() {
		String text = alertBtn.getText();
		return text;
	}
	
	public void click_alert_button() {
		alertBtn.click();
		log.info("Alert button clicked");
	}
	
	public void click_share_close_button() {
		shareCloseBtn.click();
		log.info("Share modal closed");
	}
	
	public String get_share_title_text() {
		String text = shareTitle.getText();
		return text;
	}
	
	public void click_share_button() {
		shareBtn.click();
		log.info("Share button clicked");
	}
	
	public void click_cite_modal_close_button() {
		citeCloseBtn.click();
		log.info("Cite modal close button clicked");
	}
	
	public String get_cite_label_text() {
		String text = citeLabel.getText();
		return text;
	}
	
	public void click_cite_button() {
		citeBtn.click();
		log.info("Cite button clicked");
	}
	
	public void click_remove_favourite_button() {
		removeFavouriteBtn.click();
		log.info("Remove favourite button clicked");
	}
	
	public String get_manage_favourite_title_text() {
		String text = manageFavouriteTitle.getText();
		return text;
	}
	
	public void click_manage_favourite_button() {
		manageFavouriteBtn.click();
		log.info("Manage favourite button clicked");
	}
	
	public void click_favourite_button() {
		favouriteBtn.click();
		log.info("Favourite button clicked");
	}
	
	public String get_favourite_button_text() {
		String text = favouriteBtn.getText();
		return text;
	}
	
	public void click_add_to_favourites_button() {
		addToFavouritesBtn.click();
		log.info("Add to favourites button clicked");
	}
	
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
