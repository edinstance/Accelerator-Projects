Feature: Get all the books

  Scenario: Get all books successfully
    Given books are added
    When the client requests to get all books
    Then the server returns all the books