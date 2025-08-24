@Auth @UpdateBookingById @Negative
Feature: Update booking by ID negative tests

  @InvalidID
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

  @NonExistentID
  Scenario: User should get expected error messages when trying to update booking by ID using non-existent ID
    Given user enters booking details
      | firstName | lastName  | depositPaid | email                           | phone           | checkIn    | checkOut   | roomId |
      | Tripp     | Phelipeau | true        | tphelipeau0@constantcontact.com | +1-500-673-1886 | 2025-08-28 | 2025-09-04 | 215    |
    And user logged in as an admin
    And access token is valid
    When user sends PUT request to update a booking by "123456789" ID
    #Next steps will be failing until AT-12 is resolved
    Then response code should be 404
    #TBD: update when AT-12 is fixed
    And booking response should contain the error message "Non-existent booking ID"

  @InvalidData
  Scenario Outline: User should get expected error message when trying to update a booking using invalid data
    Given user enters booking details
      | firstName | lastName  | depositPaid | email                           | phone           | checkIn    | checkOut   | roomId |
      | Tripp     | Phelipeau | true        | tphelipeau0@constantcontact.com | +1-500-673-1886 | 2025-08-28 | 2025-09-04 | 214    |
    When user sends POST request to create a booking
    Then response code should be 200
    #Next steps will be failing until AT-3 is resolved
    And booking response should match the "BookingSchema" json scheme
    And booking response data should match data given by user
    When user enters booking details
      | firstName   | lastName   | depositPaid   | email   | phone   | checkIn   | checkOut   | roomId   |
      | <firstName> | <lastName> | <depositPaid> | <email> | <phone> | <checkIn> | <checkOut> | <roomId> |
    And user logged in as an admin
    And access token is valid
    And user sends POST request to create a booking
    #Next steps will be failing until AT-13 is resolved
    Then response code should be 400
    And booking response should contain the error message "<errorMessage>"

    Examples:
      | firstName           | lastName                        | depositPaid | email                           | phone                  | checkIn    | checkOut   | roomId | errorMessage                                                |
      |                     | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 201    | Firstname should not be blank                               |
      | NameExactly19CharsL | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 202    | size must be between 3 and 18                               |
      | ab                  | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 203    | size must be between 3 and 18                               |
      | Tripp               |                                 | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 204    | Lastname should not be blank                                |
      | Tripp               | LastNameExactly31CharactersLong | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 205    | size must be between 3 and 30                               |
      | Tripp               | ab                              | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 206    | size must be between 3 and 30                               |
      | Tripp               | Phelipeau                       | true        |                                 | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 207    | must not be empty                                           |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com |                        | 2025-08-31 | 2025-09-15 | 208    | must not be empty                                           |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +123456789101112131415 | 2025-08-31 | 2025-09-15 | 209    | size must be between 11 and 21                              |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +123456789             | 2025-08-31 | 2025-09-15 | 210    | size must be between 11 and 21                              |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        |            | 2025-09-15 | 211    | must not be null                                            |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 |            | 212    | must not be null                                            |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2000-08-31 | 2025-09-15 | 213    | TBD after AT-4 is fixed                                     |