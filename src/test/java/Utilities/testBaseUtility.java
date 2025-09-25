package Utilities;

import POJOClasses.Payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class testBaseUtility {
    public Properties prop;

    public String getPropertiesValue(String key) throws IOException {
        prop = new Properties();
        FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\config.properties");
        prop.load(fi);
        return prop.getProperty(key);
    }

    public RequestSpecification reqSpecForAddPlace() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dateTime = LocalDateTime.now().format(formatter);
        PrintStream stream = new PrintStream(new FileOutputStream("logs\\logger"+dateTime+".txt"));
        Thread.sleep(1000);

        return new RequestSpecBuilder()
                .setBaseUri(getPropertiesValue("baseURL"))
                .addFilter(RequestLoggingFilter.logRequestTo(stream))
                .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                .setContentType(ContentType.JSON)
                .build();
    }
}