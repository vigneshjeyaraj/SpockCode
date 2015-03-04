#SPRING-16624 (levi/dockers/NL)
Feature: Checkout With IDeal

@Transactional_DS_NL_nl_NL @Transactional_LE_NL_nl_NL
@Regression_DS_NL_nl_NL @Regression_LE_NL_nl_NL
Scenario: Checkout With IDeal
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
And choose IDeal payment
And choose "POSTBANK" bank and confirm
Then I should get the order placed and see the order confirmation page for email
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And the order is created in Hybris with the same total amount
And access to Global Collect
And I should see the order in Global Collect
And also the "IDEAL" payment
And receive the order confirmation email in "https://www.mailinator.com"