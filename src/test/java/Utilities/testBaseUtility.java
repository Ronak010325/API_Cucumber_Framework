package Utilities;

import POJOClasses.Payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

import static Utilities.getTimeDate.getDatetime;

public class testBaseUtility {
    public Properties prop;

    public String getPropertiesValue(String key) throws IOException {
        prop = new Properties();
        FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\config.properties");
        prop.load(fi);
        return prop.getProperty(key);
    }

    public RequestSpecification reqSpecBuilder() throws IOException, InterruptedException {
        File folder = new File("logs/"+getPropertiesValue("loggerFileName"));
        folder.mkdirs();
        PrintStream stream = new PrintStream(new FileOutputStream("logs\\"+getPropertiesValue("loggerFileName")+"\\logger"+getDatetime()+".txt"));
        Thread.sleep(1000);

        return new RequestSpecBuilder()
                .setBaseUri(getPropertiesValue("baseURL"))
                .addFilter(RequestLoggingFilter.logRequestTo(stream))
                .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                .setContentType(ContentType.JSON)
                .build();
    }

    /*
    -----------------Logging can also be done in single file-----------------
    public static RequestSpecification req;
    public RequestSpecification reqSpecForAddPlace() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dateTime = LocalDateTime.now().format(formatter);
        if (req == null) {
            PrintStream stream = new PrintStream(new FileOutputStream("logs\\logger.txt"));
            req = new RequestSpecBuilder()
                    .setBaseUri(getPropertiesValue("baseURL"))
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .setContentType(ContentType.JSON)
                    .build();
            return req;
        }
        return req;
    }*/

//    This is to be used only when there is small json in response
    public String getJsonPathValue(Response res, String key) {
        String resJSON = res.asString();
        JsonPath js = new JsonPath(resJSON);
        return js.get(key).toString();
    }
}