Feature: Add and delete a book

  Scenario: Add a book successfully then delete it
    Given the book details
    When the client sends a request to the add book endpoint
    Then the book is added and returned in the response
    And the client sends a request to the delete book endpoint