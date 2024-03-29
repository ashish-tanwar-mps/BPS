 package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {".//Features//BPS//login.feature"},
		glue= "stepDefinitions",
		dryRun= false,
		monochrome= true,
		//tags= "@tag23",
		plugin= {"pretty",
				 "html:target/cucumber-report/reports_html.html",
				 "json:target/cucumber.json",
				 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class TestRunner {

}
