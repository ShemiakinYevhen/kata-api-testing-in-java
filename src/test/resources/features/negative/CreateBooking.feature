@NoAuth @CreateBooking @Negative
Feature: Create booking negative tests

  @InvalidData
  Scenario Outline: User should get expected error message when trying to create a booking using invalid data
    Given user enters booking details
      | firstName   | lastName   | depositPaid   | email   | phone   | checkIn   | checkOut   | roomId   |
      | <firstName> | <lastName> | <depositPaid> | <email> | <phone> | <checkIn> | <checkOut> | <roomId> |
    When user sends POST request to create a booking
    Then response code should be 400
    And booking response should contain the error message "<errorMessage>"

    Examples:
      | firstName           | lastName                        | depositPaid | email                           | phone                  | checkIn    | checkOut   | roomId | errorMessage                                                |
      |                     | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 401    | Firstname should not be blank                               |
      | NameExactly19CharsL | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 402    | size must be between 3 and 18                               |
      | ab                  | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 403    | size must be between 3 and 18                               |
      | Tripp               |                                 | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 404    | Lastname should not be blank                                |
      | Tripp               | LastNameExactly31CharactersLong | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 405    | size must be between 3 and 30                               |
      | Tripp               | ab                              | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 406    | size must be between 3 and 30                               |
      | Tripp               | Phelipeau                       | true        |                                 | +1-500-673-1886        | 2025-08-31 | 2025-09-15 | 407    | must not be empty                                           |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com |                        | 2025-08-31 | 2025-09-15 | 408    | must not be empty                                           |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +123456789101112131415 | 2025-08-31 | 2025-09-15 | 409    | size must be between 11 and 21                              |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +123456789             | 2025-08-31 | 2025-09-15 | 410    | size must be between 11 and 21                              |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        |            | 2025-09-15 | 411    | must not be null                                            |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2025-08-31 |            | 412    | must not be null                                            |
      | Tripp               | Phelipeau                       | true        | tphelipeau0@constantcontact.com | +1-500-673-1886        | 2000-08-31 | 2025-09-15 | 413    | TBD after AT-4 is fixed                                     |