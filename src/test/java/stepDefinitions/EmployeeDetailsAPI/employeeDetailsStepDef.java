package stepDefinitions.EmployeeDetailsAPI;

import POJOClasses.Amplify.AmplifyPayload;
import POJOClasses.Employees.employeePOJO;
import UrlBuilder.APIResources;
import Utilities.testBaseUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class employeeDetailsStepDef extends testBaseUtility {
    int id;

    @Given("build reqSpec and get URL ready")
    public void build_req_spec_and_get_url_ready() throws IOException, InterruptedException {
        reqSpec = given()
                .spec(reqSpecBuilder());
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

    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String resValue = getJsonPathValue(response, key);
        Assert.assertEquals(resValue, value);
    }

    @Given("add {string} to URL in path param")
    public void add_to_url_in_path_param(String id) throws IOException, InterruptedException {
        reqSpec = given()
                .spec(reqSpecBuilder())
                .pathParams("id",id);
    }

    @Then("{string} in the response is matching")
    public void in_the_response_is_matching(String id) {
        employeePOJO data = response.as(employeePOJO.class);
        Assert.assertEquals(data.getData().getId(), Integer.parseInt(id));
    }

    @Given("add {string} {string} and {string}")
    public void add_and(String name, String salary, String age) throws IOException, InterruptedException {
        HashMap body = new HashMap();
        body.put("name", name);
        body.put("salary", salary);
        body.put("age", age);

        reqSpec=given()
                .spec(reqSpecBuilder())
                .body(body);
    }

    @Then("the API call is success with status code {string} and Id is recorded for update")
    public void the_api_call_is_success_with_status_code_and_id_is_recorded_for_update(String string) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(string));
        id = Integer.parseInt(getJsonPathValue(response, "data.id"));
    }

    @Given("add {string} {string} {string} {string} to path param for update")
    public void add_to_path_param_for_update(String id, String name, String salary, String age) throws IOException, InterruptedException {
        HashMap body = new HashMap();
        body.put("name", name);
        body.put("salary", salary);
        body.put("age", age);

        reqSpec = given()
                .spec(reqSpecBuilder())
                .pathParams("id", id)
                .body(body);
    }

    @Then("{string} is matching to {string}")
    public void is_matching_to(String key, String value) {
        String name = getJsonPathValue(response, "data."+key);
        Assert.assertEquals(value, name);
    }

    @Given("add {string} to path param for delete request")
    public void add_to_path_param_for_delete_request(String id) throws IOException, InterruptedException {
        reqSpec = given()
                .spec(reqSpecBuilder())
                .pathParams("id", id);
    }
}
