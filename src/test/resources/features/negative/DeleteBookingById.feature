@Auth @DeleteBookingById @Negative
Feature: Delete booking by ID negative tests

  Scenario: User should get expected error messages when trying to delete booking by ID using invalid ID
    Given user logged in as an admin
    And access token is valid
    When user sends DELETE request to delete a booking by "invalid" ID
    #Next steps will be failing until AT-8 is resolved
    Then response code should be 400
    #TBD: update when AT-8 is fixed
    And booking response should contain the error message "Invalid booking ID"

  Scenario: User should get expected error messages when trying to delete booking by ID using non-existent ID
    Given user logged in as an admin
    And access token is valid
    When user sends DELETE request to delete a booking by "123456789" ID
    #Next steps will be failing until AT-12 is resolved
    Then response code should be 404
    #TBD: update when AT-12 is fixed
    And booking response should contain the error message "Non-existent booking ID"