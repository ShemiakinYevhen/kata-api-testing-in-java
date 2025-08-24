@NoAuth @CreateBooking @Positive
Feature: Create booking positive tests

  Scenario: User should be able to create a booking using valid data
    Given user enters booking details
      | firstName | lastName  | depositPaid | email                           | phone           | checkIn    | checkOut   | roomId |
      | Tripp     | Phelipeau | true        | tphelipeau0@constantcontact.com | +1-500-673-1886 | 2025-08-28 | 2025-09-04 | 430    |
    When user sends POST request to create a booking
    Then response code should be 200
    #Next steps will be failing until AT-3 is resolved
    And booking response should match the "BookingSchema" json scheme
    And booking response data should match data given by user