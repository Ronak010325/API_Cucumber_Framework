package testDataBuilder;

import POJOClasses.Location;
import POJOClasses.Payload;
import Utilities.ExcelUtilities;
import Utilities.testBaseUtility;
import com.mongodb.util.JSON;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class testDataBuild extends testBaseUtility {
    ExcelUtilities excel;
    String fileName = getPropertiesValue("excelFileName");
    String filePath = System.getProperty("user.dir")+"\\testData\\";
    String path = filePath + fileName;

    public testDataBuild() throws IOException {
    }

    public Payload getAddPlaceData(String name, String address, String language) {
//        This is Serialization
        Payload payloadBody = new Payload();
        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        payloadBody.setLocation(loc);
        payloadBody.setAccuracy(50);
        payloadBody.setName(name);
        payloadBody.setPhone_number("(+91) 983 893 3937");
        payloadBody.setAddress(address);
        List<String> types = Arrays.asList("shoe park", "shop");
        payloadBody.setTypes(types);
        payloadBody.setWebsite("http://google.com");
        payloadBody.setLanguage(language);

        return payloadBody;
    }

    public String deletePlaceData(String placeId) {
        return "{ " +
                "\"place_id\":\""+placeId+"\"" +
                "}";
    }

    public HashMap getBookData(String SheetName, int rowNum) throws IOException {
        excel = new ExcelUtilities(path, SheetName);
        int cellCount = excel.getCellCount(rowNum);
        HashMap<String, String> output = new HashMap<String, String>();
        for (int i = 0 ; i < cellCount ; i++) {
            output.put(excel.getCellValue(0, i), excel.getCellValue(rowNum, i));
        }
        return output;
    }

    public String deleteBookData(String SheetName, int rowNum) throws IOException {
        excel = new ExcelUtilities(path, SheetName);
        String bookId = excel.getCellValue(rowNum, 0);
        return "{ " +
                "\"ID\":\""+bookId+"\"" +
                "}";
    }

    public String getBookId(String sheetName, int rowNum) throws IOException {
        excel = new ExcelUtilities(path, sheetName);
        String isbn = excel.getCellValue(rowNum, 1);
        String aisle = excel.getCellValue(rowNum, 2);
        return isbn+aisle;
    }

    public String getAuthorName(String sheetName, int rowNum) throws IOException {
        excel = new ExcelUtilities(path, sheetName);
        return excel.getCellValue(rowNum, 3);
    }

    public String getAmplifyBody() {
        return "{\n" +
                "    \"messages\": [\n" +
                "        {\n" +
                "            \"role\": \"user\", \n" +
                "            \"content\": \"What are the three primary colours?\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"model_id\":\"8cac310c-065b-4866-827d-cdac270f7fb7\"\n" +
                "}";
    }
}
