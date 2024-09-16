Feature: Get all the authors

  Scenario: Get all authors successfully
    Given authors are added
    When the client requests to get all authors
    Then the server returns all the authors
