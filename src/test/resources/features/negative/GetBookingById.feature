@Auth @GetBookingById @Negative
Feature: Get booking by ID negative tests

  Scenario: User should get expected error messages when trying to get booking by ID using invalid data
    Given user logged in as an admin
    And access token is valid
    When user sends GET request to get a booking by "invalid" ID
    Then response code should be 400
    #TBD: update when AT-6 is fixed
    And booking response should contain the error message "Invalid booking ID"