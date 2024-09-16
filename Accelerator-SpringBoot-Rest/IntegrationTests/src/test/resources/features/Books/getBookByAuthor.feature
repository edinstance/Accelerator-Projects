Feature: get books by their author

  Scenario: get books with a certain author successfully
    Given there are books with that author
    When the client requests for an authors books
    Then the server returns the books related to the author