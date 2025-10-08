package stepDefinitions.AmplifyAPI;

import POJOClasses.Amplify.AmplifyBodyPayload;
import POJOClasses.Amplify.AmplifyPayload;
import UrlBuilder.APIResources;
import Utilities.testBaseUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import testDataBuilder.testDataBuild;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class amplifyStepDef extends testBaseUtility {
    Response response;
    testDataBuild dataBuilder = new testDataBuild();
    RequestSpecification reqSpec;

    public amplifyStepDef() throws IOException {
    }

    @Given("Prepare request for first prompt")
    public void prepare_request_for_first_prompt() throws IOException, InterruptedException {
        String apiToken = getPropertiesValue("api_token");
        AmplifyBodyPayload body = dataBuilder.getAmplifyBody();
        reqSpec = given()
                .spec(reqSpecBuilder())
                .header("token",apiToken)
                .body(body);
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
        AmplifyPayload responseData = response.as(AmplifyPayload.class);
        System.out.println(responseData.getAssistant_resp());
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(string));
    }
}
