Feature: Validating Place API's

#Scenario: Verify if the Place is being added successfully using AddPlaceAPI
#  Given Add Place Payload "single" "single test address" "demo"
#  When user calls "AddPlaceAPI" with "POST" request
#  Then the API call is success with status code "200"
#  And "status" in response body is "OK"
#  And "scope" in response body is "APP"
#
#Scenario: Verify if the Place is being deleting successfully using AddPlaceAPI
#    Given Add Place Payload "single" "single test address" "demo"
#    When user calls "DeletePlaceAPI" with "POST" request
#    Then the API call is success with status code "200"

#  Can be reused using parameterization concept within one single method
#  When user calls "DeletePlaceAPI" with POST request

#Data Driven
  @AddPlaceAPI
  Scenario Outline: Verify if the Place is being added successfully using AddPlaceAPI
    Given Add Place Payload "<name>" "<address>" "<language>"
    When user calls "AddPlaceAPI" with "POST" request
    Then the API call is success with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to the "<name>" using "GetPlaceAPI"

    Examples:
    | name  | address     | language  |
    | ronak | Nallasopara | Hindi     |
    | rahul | Virar       | Marathi   |

# Once one scenario is completed all the non static variables will be reset to NULL.
# So if the data is passing from one scenario to another then use "static" keyword.

  @DeletePlaceAPI
  Scenario: Verify if the Place is being Deleted successfully using DeletePlaceAPI
    Given Delete Place Payload
    When user calls "DeletePlaceAPI" with "POST" request
    Then the API call is success with status code "200"
    And "status" in response body is "OK"