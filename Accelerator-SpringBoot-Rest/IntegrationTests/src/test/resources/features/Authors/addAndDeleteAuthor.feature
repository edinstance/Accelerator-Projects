Feature: Add and delete an author

  Scenario: Add an author successfully then delete it
    Given the author details
    When the client sends a request to the add author endpoint
    Then the author is added and returned in the response
    And the client sends a request to the delete author endpoint
