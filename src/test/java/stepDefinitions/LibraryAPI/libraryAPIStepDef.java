package stepDefinitions.LibraryAPI;

import POJOClasses.Payload;
import UrlBuilder.APIResources;
import Utilities.testBaseUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import testDataBuilder.testDataBuild;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class libraryAPIStepDef extends testBaseUtility {
    Response response;
    HashMap libraryBody;
    testDataBuild dataBuilder = new testDataBuild();
    RequestSpecification reqSpec;

    public libraryAPIStepDef() throws IOException {
    }

    @Given("Add Book Payload Using {string} and {string}")
    public void add_book_payload_using_and(String sheetName, String rowNum) throws IOException, InterruptedException {
        int row = Integer.parseInt(rowNum);
        libraryBody = dataBuilder.getBookData(sheetName, row);
        reqSpec = given()
                .spec(reqSpecBuilder())
                .body(libraryBody);
    }

    @Given("Delete Book Payload Using {string} and {string}")
    public void delete_book_payload_using_and(String sheetName, String rowNum) throws IOException, InterruptedException {
        int row = Integer.parseInt(rowNum);
        String body = dataBuilder.deleteBookData(sheetName, row);
        reqSpec = given()
                .spec(reqSpecBuilder())
                .body(body);
    }

    @Given("Get Book By ID Payload Using {string} and {string}")
    public void get_book_by_id_payload_using_and(String sheetName, String rowNum) throws IOException, InterruptedException {
        int row = Integer.parseInt(rowNum);
        String bookId = dataBuilder.getBookId(sheetName, row);
        reqSpec = given()
                .spec(reqSpecBuilder())
                .queryParam("ID", bookId);
    }

    @Given("Get Book By Name Payload Using {string} and {string}")
    public void get_book_by_name_payload_using_and(String sheetName, String rowNum) throws IOException, InterruptedException {
        int row = Integer.parseInt(rowNum);
        String name = dataBuilder.getAuthorName(sheetName, row);
        reqSpec = given()
                .spec(reqSpecBuilder())
                .queryParam("AuthorName",name);
    }

    @When("user calls {string} with {string} request")
    public void user_calls_with_request(String resource, String requestType) {
        APIResources apiResource = APIResources.valueOf(resource);
        String path = apiResource.getResource();
        switch (requestType.toLowerCase()) {
            case "get" -> {
                response = reqSpec.when().get(path);
            }
            case "post" -> {
                response = reqSpec.when().post(path);
            }
            case "put" -> {
                response = reqSpec.when().put(path);
            }
            case "patch" -> {
                response = reqSpec.when().patch(path);
            }
            case "delete" -> {
                response = reqSpec.when().delete(path);
            }
            default -> {
                Assert.fail("No Request Specified !");
            }
        }
    }

    @Then("the API call is success with status code {string}")
    public void the_api_call_is_success_with_status_code(String string) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(string));
    }

    @Then("verify if the author Name maps to the original data using {string} and {string}")
    public void verify_if_the_author_name_maps_to_the_original_data_using_and(String sheetName, String rowNum) throws IOException {
        int row = Integer.parseInt(rowNum);
        String authorName = response.jsonPath().get("[0].author");
        String verifyName = dataBuilder.getAuthorName(sheetName, row);
        Assert.assertEquals(authorName, verifyName);
    }

    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String resValue = getJsonPathValue(response, key);
        Assert.assertEquals(resValue, value);
    }
}