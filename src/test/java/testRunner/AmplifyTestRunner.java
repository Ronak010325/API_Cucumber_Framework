package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features/amplifyAPI.feature",   // path to your .feature files.
        glue = {"stepDefinitions.AmplifyAPI"},            // package name of your step def
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed_scenarios.txt"     //write the file path of which scenario failed to execute
        }
)

public class AmplifyTestRunner extends AbstractTestNGCucumberTests {
}
