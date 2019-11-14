Feature:

#  @web
#  Scenario: Create and delete booking with valid data - Positive test
#    Given the user is on the "hotel_booking" page
#    And submits the "hotel_booking_form" with "valid_details"
#      | first_name | Test         |
#      | last_name  | TestLastName |
#      | price      | 20           |
#      | deposit    | false        |
#      | check_in   | 2019-11-14   |
#      | check_out  | 2019-11-16   |
#    Then the user is "Displayed" on the page
#
#    #####################################
#    #    DELETES THE REGISTERED USER    #
#    When user clicks on the the delete button
#    Then the user is "Not Displayed" on the page

  @api
  Scenario: Create and delete booking with valid data - Positive API Test

    When the user sends a "POST" request "booking.json" to the "hotel_booking_endpoint"
    Then the user gets a "200" successful response

    When the performs a GET request to the "hotel_booking_endpoint"
    Then the user gets a "200" successful response
    And the booking is registered

#    When the user sends a "DELETE" request "booking.json" to the "hotel_booking_endpoint"
#    Then the user gets a "201" successful response



    #####################################
    #    DELETES THE REGISTERED USER    #
#    When user clicks on the the delete button
#    Then the user is "Not Displayed" on the page








#  @web
#  Scenario: Create booking with valid data - Negative test
#    Given the user is on the "hotel_booking_form" page
#    And completes the "hotel_booking_form" with "valid_details"
#  @test
#  Scenario: Negative test with invalid data
#    Given the user is on the "hotel_booking_form" page
#    And completes the "hotel_booking_form" with "valid_details"
#      | first_name | test  |
#      | last_name  | test  |
#      | price      | 20    |
#      | deposit    | false |
#      | check_in   | +2    |
#      | check_out  | +4    |