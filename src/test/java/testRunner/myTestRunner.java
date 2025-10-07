package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;

@CucumberOptions(
        features = "src/test/java/features/login.feature",   // path to your .feature files.
                                               // You Can Also give the exact path to the feature file like
                                               // src/test/java/features/login.feature
        glue = {"stepDefinitions.LoginStepDef"},            // package name of your step def
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed_scenarios.txt"     //write the file path of which scenario failed to execute
        }
//        tags = "@DeletePlaceAPI"
)

public class myTestRunner extends AbstractTestNGCucumberTests {
}
