package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CommonFunctions {

	public WebDriver driver =WebDriverController.driver;
	private PropertyFile prop = new PropertyFile();
	Properties p = prop.propertyf();
	static Logger log = Logger.getLogger(CommonFunctions.class);
	List<WebElement> elms;

	public String create_Xpath(String str1, String str2, String str3) 
	{
		String concatenate = p.getProperty(str1) + str2 + p.getProperty(str3);
		return concatenate;
	}
	
	public String create_footer_xpath(String str1,String str2,String str3,String str4, String str5) {
		String concatenate = p.getProperty(str1) + str2 + p.getProperty(str3) + str4 + p.getProperty(str5);
		return concatenate;
	}
	
	public void user_get_link_and_there_title_of_page(List<WebElement> WebelementList) throws InterruptedException {

//		Thread.sleep(3000);
		List<WebElement> WebElemntContainer = WebelementList;

		log.info("********WebLink titles***************************");
		for (WebElement Weblink : WebElemntContainer) {
			log.info("Weblink Title :" + Weblink.getText());

		}
		log.info("********Weblink titles printed***************************");

	}
	
	public void user_verify_the_value_through_facet_text(String actualtext, String expectedtext) {


		log.info("Actual text is" + actualtext);
		log.info("Expected text is" + expectedtext);
		
		if (actualtext.equalsIgnoreCase(expectedtext)) {

			// System.out.println("Facet text verified. Navigation Successful");
			log.info("Facet issue text verified. Navigation Successful");

		} else {
			System.out.println("Facet text not verified");
			log.info("Facet issue text not verified");

		}
		}
	
	public void loadAllElements() {
		List<WebElement> el = driver.findElements(By.xpath("//div[@class='search-item__body']"));
        int count=0;
        for ( WebElement e : el ) {
         System.out.println( e.getTagName()+"    "+e.getText());

         count++;

        }
      System.out.println(count );
	}
	
	public WebElement createDynamicxpath(String str1, String str2, String str3) {

		System.out.println("Parameter 1 :"+str1);
		System.out.println("Parameter 2 :"+str2);
		System.out.println("Parameter 3 :"+str3);
		String concatenate = p.getProperty(str1) + str2 + str3;
		System.out.println("Full Xpath is :"+concatenate);
		WebElement elm = driver.findElement(By.xpath(concatenate));
		System.out.println("Xpath is :"+elm.getText());
		log.info("Facet Title xpath is " +elm.getText());
		return elm;
		
	}
	public List<WebElement> createDynamicxpathList(String str1, String str2, String str3) {

		System.out.println("Parameter 1 :"+str1);
		System.out.println("Parameter 2 :"+str2);
		System.out.println("Parameter 3 :"+str3);
		String concatenate = str1+ p.getProperty(str2) + p.getProperty(str3);
		List<WebElement> elms = driver.findElements(By.xpath(concatenate));
		System.out.println("Xpath is :"+elms.toString());
		return elms;
		
	}
	
	public WebElement Mainfacet(String str1, String str2, String str3) {

		System.out.println("Parameter 1 :"+str1);
		System.out.println("Parameter 2 :"+str2);
		System.out.println("Parameter 3 :"+str3);
		String concatenate = p.getProperty(str1) + str2 + str3;
		System.out.println("Full Xpath is :"+concatenate);
		WebElement elm = driver.findElement(By.xpath(concatenate));
		System.out.println("Xpath is :"+elm.getText());
		log.info("Facet Title xpath is " +elm.getText());
		return elm;
		
	}
	
	public ArrayList<String> user_get_all_link_and_there_title(List<WebElement> WebelementList)
			throws InterruptedException {

		Thread.sleep(3000);
		List<WebElement> resourcePdfLinks = WebelementList;
		ArrayList<String> a2 = new ArrayList<String>();

		log.info("********Resource Pdf titles***************************");
		for (WebElement resPdf : resourcePdfLinks) {
			log.info("Resource Pdf link Title :" + resPdf.getText());
			a2.add(resPdf.getText());
		}
		return a2;

	}
	
}