Feature: Get Books By User ID

  Scenario: Register an author and retrieve their books
    Given the user is an author and has books
    Then the user is registered
    Then books are added to the user
    Then the users books are requested