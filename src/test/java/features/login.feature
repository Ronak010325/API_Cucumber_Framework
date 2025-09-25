Feature: Validating Place API's

Scenario: Verify if the Place is being added successfully using AddPlaceAPI
  Given Add Place Payload "single" "single test address" "demo"
  When user calls "AddPlaceAPI" with POST request
  Then the API call is success with status code "200"
  And "status" in response body is "OK"
  And "scope" in response body is "APP"


#  Can be reused using parameterization concept within one single method
#  When user calls "DeletePlaceAPI" with POST request

#Data Driven
  Scenario Outline: Verify if the Place is being added successfully using AddPlaceAPI
    Given Add Place Payload "<name>" "<address>" "<language>"
    When user calls "AddPlaceAPI" with POST request
    Then the API call is success with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Examples:
    | name  | address     | language |
    | ronak | Nallasopara | Hindi     |
    | rahul | Virar       | Marathi   |