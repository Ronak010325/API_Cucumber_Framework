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

public class loginStepDef extends testBaseUtility {
    Response resOfAddPlace;
    Response resOfGetPlace;
    Payload body;
    testDataBuild dataBuilder = new testDataBuild();
    RequestSpecification reqSpec;
    String placeId;

    @Given("Add Place Payload {string} {string} {string}")
    public void Add_Place_Payload(String name, String address, String language) throws IOException, InterruptedException {
        body = dataBuilder.getAddPlaceData(name, address, language);
        reqSpec = given()
                .spec(reqSpecForAddPlace())
                .body(body);
    }

    @When("user calls {string} with {string} request")
    public void user_calls_with_POST_request(String resource, String requestType) {
        APIResources apiResource = APIResources.valueOf(resource);
        String path = apiResource.getResource();
        switch(requestType.toLowerCase()) {
            case "get" -> {
                resOfAddPlace = reqSpec.when().get(path);
                placeId = resOfAddPlace.jsonPath().get("place_id").toString();
            }
            case "post" -> {
                resOfAddPlace = reqSpec.when().post(path);
                placeId = resOfAddPlace.jsonPath().get("place_id").toString();
            }
            case "put" -> {
                resOfAddPlace = reqSpec.when().put(path);
                placeId = resOfAddPlace.jsonPath().get("place_id").toString();
            }
            case "patch" -> {
                resOfAddPlace = reqSpec.when().patch(path);
                placeId = resOfAddPlace.jsonPath().get("place_id").toString();
            }
            case "delete" -> {
                resOfAddPlace = reqSpec.when().delete(path);
                placeId = resOfAddPlace.jsonPath().get("place_id").toString();
            }
            default -> {
                Assert.fail("No Request Specified !");
            }
        }
    }

    @Then("the API call is success with status code {string}")
    public void the_api_call_is_success_with_status_code(String string) {
        Assert.assertEquals(resOfAddPlace.getStatusCode(), Integer.parseInt(string));
    }

    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String resValue = resOfAddPlace.jsonPath().get(key);
        Assert.assertEquals(resValue, value);
    }

    @Then("verify place_Id created maps to the {string} using {string}")
    public void verify_place_id_created_maps_to_the_using(String name, String resource) {
        APIResources apiResource = APIResources.valueOf(resource);
        String path = apiResource.getResource();
        resOfGetPlace = given()
                .spec(reqSpec)
                .queryParam("place_id", placeId)
                .queryParam("key", prop.getProperty("key"))
                .when()
                .get(path);
        String resName = resOfGetPlace.jsonPath().get("name").toString();
        Assert.assertEquals(resName, name);
    }
}