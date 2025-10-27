package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features/employeeDetailsAPI.feature",   // path to your .feature files.
        glue = {"stepDefinitions.EmployeeDetailsAPI"},            // package name of your step def
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed_scenarios.txt"     //write the file path of which scenario failed to execute
        },
// and means scenario that have both tags
// or means all scenario that have these tags
//        tags = "@CreateEmployeeDetails or @UpdateEmployeeDetails"
        tags = "@DeleteEmployeeDetails"
)

public class EmployeeDetailsRunner extends AbstractTestNGCucumberTests {
}
