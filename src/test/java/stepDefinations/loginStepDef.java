package stepDefinations;

import static io.restassured.RestAssured.*;

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

public class loginStepDef extends testBaseUtility {
    Response response;
    Payload body;
    testDataBuild dataBuilder = new testDataBuild();
    RequestSpecification reqSpec;
    static String placeId;  //As this place id is needed in different scenarios it's declared as static

    @Given("Add Place Payload {string} {string} {string}")
    public void Add_Place_Payload(String name, String address, String language) throws IOException, InterruptedException {
        body = dataBuilder.getAddPlaceData(name, address, language);
        reqSpec = given()
                .spec(reqSpecBuilder())
                .body(body);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException, InterruptedException {
        HashMap<String, String> body = dataBuilder.deletePlaceData(placeId);
        reqSpec = given()
                .spec(reqSpecBuilder())
                .body(body);
    }

    @When("user calls {string} with {string} request")
    public void user_calls_with_request(String resource, String requestType) {
        APIResources apiResource = APIResources.valueOf(resource);
        String path = apiResource.getResource();
        switch(requestType.toLowerCase()) {
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

    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String resValue = getJsonPathValue(response, key);
        Assert.assertEquals(resValue, value);
    }

    @Then("verify place_Id created maps to the {string} using {string}")
    public void verify_place_id_created_maps_to_the_using(String name, String resource) throws IOException, InterruptedException {
        placeId = getJsonPathValue(response, "place_id");
        reqSpec = given()
                .spec(reqSpecBuilder())
                .queryParam("place_id", placeId)
                .queryParam("key", prop.getProperty("key"));
        user_calls_with_request(resource, "GET");
        String resName = getJsonPathValue(response, "name");
        Assert.assertEquals(resName, name);
    }
}