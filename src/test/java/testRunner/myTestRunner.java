package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",   // path to your .feature files.
                                               // You Can Also give the exact path to the feature file like
                                               // src/test/java/features/login.feature
        glue = {"stepDefinations"},            // package name of your step def
        plugin = {
                "pretty",
                "html:Reports/htmlReports/cucumber-report.html",
                "json:Reports/jsonReports/cucumber.json",
                "rerun:target/failed_scenarios.txt"     //write the file path of which scenario failed to execute
        }
//        tags = "@DeletePlaceAPI"
)

public class myTestRunner extends AbstractTestNGCucumberTests {
}
