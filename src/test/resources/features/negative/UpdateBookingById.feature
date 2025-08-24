@Auth @UpdateBookingById @Negative
Feature: Update booking by ID negative tests

  Scenario: User should get expected error messages when trying to update booking by ID using invalid ID
    Given user enters booking details
      | firstName | lastName  | depositPaid | email                           | phone           | checkIn    | checkOut   | roomId |
      | Tripp     | Phelipeau | true        | tphelipeau0@constantcontact.com | +1-500-673-1886 | 2025-08-28 | 2025-09-04 | 215    |
    And user logged in as an admin
    And access token is valid
    When user sends PUT request to update a booking by "invalid" ID
    #Next steps will be failing until AT-8 is resolved
    Then response code should be 400
    #TBD: update when AT-8 is fixed
    And booking response should contain the error message "Invalid booking ID"