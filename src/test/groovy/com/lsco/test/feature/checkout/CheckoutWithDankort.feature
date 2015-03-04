#SPRING-16623 (levi/dockers/DK)
Feature: Checkout With Dankort

@Transactional_DS_DK_dk_DK @Transactional_LE_DK_dk_DK
@Regression_DS_DK_dk_DK @Regression_LE_DK_dk_DK
Scenario: Checkout With Dankort
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
And choose Dankort credit card
And enter the credit card number "5019994000124034", the security code "747", the expiration month "12" and expiration year "14"
Then I should get the order placed and see the order confirmation page for email
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And the order is created in Hybris with the same total amount
And access to Global Collect
And I should see the order in Global Collect
And also the "DANKORT" payment
And receive the order confirmation email in "https://www.mailinator.com"