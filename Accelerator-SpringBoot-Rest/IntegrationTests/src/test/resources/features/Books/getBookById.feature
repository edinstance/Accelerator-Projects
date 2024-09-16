Feature: Get a book by its id

  Scenario: Get the book successfully
    Given there is a book with an id
    When the client requests to get the book using the id
    Then the server returns the correct book