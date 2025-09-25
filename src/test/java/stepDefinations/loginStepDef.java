package stepDefinations;

import static io.restassured.RestAssured.*;

import POJOClasses.Payload;
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
    Payload body;
    testDataBuild dataBuilder = new testDataBuild();
    RequestSpecification reqSpec;

    @Given("Add Place Payload {string} {string} {string}")
    public void Add_Place_Payload(String name, String address, String language) throws IOException, InterruptedException {
        body = dataBuilder.getAddPlaceData(name, address, language);
        reqSpec = given()
                .spec(reqSpecForAddPlace())
                .body(body);
    }

    @When("user calls {string} with POST request")
    public void user_calls_with_POST_request(String string) {
        resOfAddPlace = reqSpec
                .when()
                .post("/maps/api/place/add/json");
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
}