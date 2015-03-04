#SPRING-17939
Feature: Profile Edit Customer Information

  @NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
  @Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
  Scenario: Profile Edit Customer Information
    Given an guest user
    When creating a new email in "https://www.mailinator.com" with domain "@mailinator.com"
    When accessing the homepage
    And accessing the registration page
    And register a new random user
    And system logout
    And receives the registration confirmation email in "https://www.mailinator.com".
    Then creating a new email in "https://www.mailinator.com" with domain "@mailinator.com"
    And accessing the homepage
    And accessing the login page
    And login as random registered user
    And udpdate profile info
    And system logout
    And receives the registration confirmation email in "https://www.mailinator.com".
    And accessing the homepage
    And accessing the login page
    And login as random registered user
    And verify new random user data
    And accessing the homepage
    And adding "1" product to the shopping bag
    And accessing the bag page
    And accessing the checkout page
    And enter the shipping information for random user with: address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
    And enter the contact information for random user with: phone "shipping.telephone"
    And check the age restriction
    And submit the information
    And choose MasterCard credit card
    And enter the credit card number "5555555555554444", the security code "111", the expiration month "01" and expiration year "16"
    And I should get the order placed and see the order confirmation page for email
    And system logout
    And receive the order confirmation email in my inbox at "https://www.mailinator.com"
    And clear browser cookies
    And accessing the homepage
    And accessing the login page
    And login as random registered user
    And verify new random user data
    And access to Hybris Management Console (username "levi.team", password = "pass1234")
    And delete random user