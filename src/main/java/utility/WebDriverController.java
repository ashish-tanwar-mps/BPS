package utility;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverController {
public static WebDriver driver;
PropertyFile propp = new PropertyFile();
Properties p = propp.propertyf();

public WebDriver initialize_webdriver() {

	if (p.getProperty("Browser").equalsIgnoreCase("FireFox")) {

		driver = new FirefoxDriver();

		driver.get(p.getProperty("Url"));
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	


	} else if (p.getProperty("Browser").equalsIgnoreCase("FireFoxHeadless")) {

		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless");
		driver = new FirefoxDriver(options);
		driver.get(p.getProperty("URL"));
		driver.manage().window().maximize();
		


	} else if (p.getProperty("Browser").equalsIgnoreCase("Chrome")) {
		driver = new ChromeDriver();

		driver.get(p.getProperty("URL"));
		driver.manage().window().maximize();
		

		
	} else {
		System.out.println("--------No Browser information provided----------");
	}

	return driver;
}

public WebDriver rdriver() {
	return driver;

}

}

	
	
	
