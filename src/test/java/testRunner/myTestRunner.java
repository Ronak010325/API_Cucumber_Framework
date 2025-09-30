package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",   // path to your .feature files
        glue = {"stepDefinations"},            // package name of your step def
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed_scenarios.txt"     //write the file path of which scenario failed to execute
        },
        tags = "@DeletePlaceAPI"
)

public class myTestRunner extends AbstractTestNGCucumberTests {
}
