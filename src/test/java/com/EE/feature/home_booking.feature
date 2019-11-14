Feature:

  Background:
    Given the user is on the "hotel_booking" page

#  @web
  Scenario: Create and delete booking with valid data - Positive test
    When user submits the "hotel_booking_form" with "valid_details"
      | first_name | Test         |
      | last_name  | TestLastName |
      | price      | 20           |
      | deposit    | false        |
      | check_in   | 2019-11-14   |
      | check_out  | 2019-11-16   |
    Then the booking is "Displayed" on the page

    #####################################
    #    DELETES THE REGISTERED USER    #
    When user clicks on the the delete button
    Then the booking is "Not Displayed" on the page


  @api @web
  Scenario: Create and delete booking with valid data - Positive API Test

    When the user sends a "POST" request "booking.json" to the "hotel_booking_endpoint"
    Then the user gets a "200" successful response

    When the performs a GET request to the "hotel_booking_endpoint"
    Then the user gets a "200" successful response
    And the booking is registered

    #####################################
    #    DELETES THE REGISTERED USER    #
    Given the registered booking is displayed on the UI
    When the user deletes the booking from the UI
    Then the booking is "Not Displayed" on the page