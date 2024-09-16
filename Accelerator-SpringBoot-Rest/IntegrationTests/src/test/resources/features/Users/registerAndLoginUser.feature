Feature: Register and Login a user

  Scenario: Register and Login a user
    Given the user details
    When the client sends the register request
    Then the client sends the login request