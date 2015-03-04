#SPRING-18087
Feature: Checkout With Visa

@Transactional_DS_IT_it_IT @Transactional_LE_IT_it_IT
@Regression_DS_IT_it_IT @Regression_LE_IT_it_IT
Scenario: Checkout With Visa
Given an guest user
When creating a new email in "https://www.mailinator.com" with domain "@mailinator.com"
And accessing the homepage 
And adding "1" product to the shopping bag
And accessing the bag page
And accessing the checkout page
And enter the shipping information: name "Martin", surname "QALevis", address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
And enter the contact information: email and phone "shipping.telephone"
And check the age restriction
And submit the information
And choose Visa credit card
And enter the credit card number "4111111111111111", the security code "111", the expiration month "01" and expiration year "16"
Then I should get the order placed and see the order confirmation page for email
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And the order is created in Hybris with the same total amount
And access to Global Collect
And I should see the order in Global Collect
And also the "VISA" payment with the same total amount
And receive the order confirmation email in my inbox at "https://www.mailinator.com"
