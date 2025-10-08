Feature: Trying Amplify API

  @Master
  Scenario: Enter First Prompt on Amplify
    Given Prepare request for first prompt
    When user calls "FirstPrompt" with "POST" request
    Then the API call is success with status code "200"