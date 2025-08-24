@Auth @UpdateBookingById @Positive
Feature: Update booking by ID positive tests

  Scenario: User should be able to update a booking using valid ID
    Given user enters booking details
      | firstName | lastName  | depositPaid | email                           | phone           | checkIn    | checkOut   | roomId |
      | Tripp     | Phelipeau | true        | tphelipeau0@constantcontact.com | +1-500-673-1886 | 2025-08-28 | 2025-09-04 | 433    |
    When user sends POST request to create a booking
    Then response code should be 200
    #Next steps will be failing until AT-3 is resolved
    And booking response should match the "BookingSchema" json scheme
    And booking response data should match data given by user
    When user logged in as an admin
    And access token is valid
    And user enters booking details
      | firstName  | lastName | depositPaid | email               | phone           | checkIn    | checkOut   | roomId |
      | Isidore    | Georgius | false       | igeorgius1@live.com | +1-895-741-8567 | 2025-09-25 | 2025-10-08 | 434    |
    And user sends PUT request to update a booking using saved data
    #Next steps will be failing until AT-9 is resolved
    Then response code should be 200
    And booking response should match the "BookingSchema" json scheme
    And booking response data should match data given by user