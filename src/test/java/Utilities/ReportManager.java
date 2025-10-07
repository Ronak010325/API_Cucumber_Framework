package Utilities;

import org.testng.annotations.AfterSuite;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager extends testBaseUtility {

    @AfterSuite
    public void moveReportsWithTimestamp() {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            File htmlReport = new File("target/cucumber-report.html");
            File jsonReport = new File("target/cucumber.json");

            File htmlDestFolder = new File("Reports/" + getPropertiesValue("loggerFileName") + "/htmlReports");
            File jsonDestFolder = new File("Reports/" + getPropertiesValue("loggerFileName") + "/jsonReports");
            htmlDestFolder.mkdirs();
            jsonDestFolder.mkdirs();

            if (htmlReport.exists()) {
                Files.move(htmlReport.toPath(), Paths.get(htmlDestFolder.getPath() + "/cucumber-report_" + timestamp + ".html"));
            }
            if (jsonReport.exists()) {
                Files.move(jsonReport.toPath(), Paths.get(jsonDestFolder.getPath() + "/cucumber_" + timestamp + ".json"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
