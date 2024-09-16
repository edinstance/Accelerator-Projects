Feature: Get an Author by its id

  Scenario: Get the author successfully
    Given there is an author with an id
    When the client requests to get the author using the id
    Then the server returns the correct author