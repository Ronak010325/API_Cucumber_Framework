Feature: validating Employee Details API

  @GetAllEmployee
  Scenario: Verify that All employee Records are being sent in the Response
    Given build reqSpec and get URL ready
    When user calls "GetEmployeesDetailsAPI" with "GET" request
    Then the API call is success with status code "200"
    And "status" in response body is "success"

  @GetSpecificEmployee
  Scenario Outline: Verify get specific employee Details from Id
    Given add "<id>" to URL in path param
    When user calls "GetEmployeeDetailsAPI" with "GET" request
    Then the API call is success with status code "200"
    And "<id>" in the response is matching
    Examples:
    | id |
    | 1  |

  @CreateEmployeeDetails
  Scenario Outline: Verify create employee Details is working or not
    Given add "<name>" "<salary>" and "<age>"
    When user calls "AddEmployeeDetailsAPI" with "POST" request
    Then the API call is success with status code "200" and Id is recorded for update
    And "status" in response body is "success"
    Examples:
    | name  | salary  | age |
    | demo  | 1234    | 20  |

  @UpdateEmployeeDetails
  Scenario Outline: Verify update employee Details is working or not
#    Given get payload ready for update request or previous request
    Given add "<id>" "<name>" "<salary>" "<age>" to path param for update
    When user calls "UpdateEmployeeDetailsAPI" with "PUT" request
    Then the API call is success with status code "200"
    And "status" in response body is "success"
    And "name" is matching to "<name>"
    Examples:
    | id | name  | salary | age |
    | 1  | ronak | 20564  | 20  |

  @DeleteEmployeeDetails
  Scenario Outline: Verify delete employee Details is working or not
    Given add "<id>" to path param for delete request
    When user calls "DeleteEmployeeDetailsAPI" with "DELETE" request
    Then the API call is success with status code "200"
    And "status" in response body is "success"
    Examples:
    | id |
    | 1  |