@Auth @GetBookingById @Negative
Feature: Get booking by ID negative tests

  @InvalidID
  Scenario: User should get expected error messages when trying to get booking by ID using invalid ID
    Given user logged in as an admin
    And access token is valid
    When user sends GET request to get a booking by "invalid" ID
    #Next steps will be failing until AT-6 is resolved
    Then response code should be 400
    #TBD: update when AT-6 is fixed
    And booking response should contain the error message "Invalid booking ID"

  @NonExistentID
  Scenario: User should get expected error messages when trying to get booking by ID using non-existent ID
    Given user logged in as an admin
    And access token is valid
    When user sends GET request to get a booking by "123456789" ID
    #Next steps will be failing until AT-11 is resolved
    Then response code should be 404
    #TBD: update when AT-11 is fixed
    And booking response should contain the error message "Non-existent booking ID"