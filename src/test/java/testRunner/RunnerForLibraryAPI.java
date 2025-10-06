package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features/LibraryAPI.feature",
        glue = {"stepDefinitions"},            // package name of your step def
        plugin = {
                "pretty",
                "html:Reports/htmlReports/cucumber-report.html",
                "json:Reports/jsonReports/cucumber.json",
                "rerun:target/failed_scenarios.txt"     //write the file path of which scenario failed to execute
        }
//        tags = "@Master"
)

public class RunnerForLibraryAPI extends AbstractTestNGCucumberTests {
}
