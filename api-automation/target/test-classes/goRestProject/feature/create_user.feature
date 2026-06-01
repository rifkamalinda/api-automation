Feature: Create user

  Scenario: create a new user successfully
    Given I prepare a valid user payload
    When I call create user API
    Then the response status should be 201
    And the response time should be less than configured timeout
    And the response body should contain name "rifkaM"