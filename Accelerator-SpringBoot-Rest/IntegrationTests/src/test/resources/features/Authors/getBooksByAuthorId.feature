Feature: Get books by an author id

  Scenario: Add an author with a book and then get it using the author id
    Given an author with a book is added
    When the authors books are requested
    Then the correct books are returned
