@NoAuth @CreateBooking @Negative
Feature: Create booking negative tests

  Scenario Outline: User should get expected error message when trying to create a booking using invalid data
    Given user enters booking details
      | firstName   | lastName   | depositPaid   | email   | phone   | checkIn   | checkOut   | roomId   |
      | <firstName> | <lastName> | <depositPaid> | <email> | <phone> | <checkIn> | <checkOut> | <roomId> |
    When user sends POST request to create a booking
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