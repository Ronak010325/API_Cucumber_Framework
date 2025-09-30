package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",   // path to your .feature files
        glue = {"stepDefinations"},                 // package name of your step defs
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
        tags = "@DeletePlaceAPI"
)

public class myTestRunner extends AbstractTestNGCucumberTests {
}
