package stepDefinitions;

import io.cucumber.java.Before;
import stepDefinitions.LoginStepDef.loginStepDef;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlaceAPI")
    public void beforeScenario() throws IOException, InterruptedException {
        loginStepDef steps = new loginStepDef();
        if (loginStepDef.placeId == null) { //Should Execute only when only deletePlaceAPI is hit.
            steps.Add_Place_Payload("ronak", "address", "language");
            steps.user_calls_with_request("AddPlaceAPI", "POST");
            steps.verify_place_id_created_maps_to_the_using("ronak", "GetPlaceAPI");
        }
    }
}
