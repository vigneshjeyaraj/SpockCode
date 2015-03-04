#SPRING-16614 (levi/dockers/GB)
Feature: Checkout With Maestro

@Transactional_DS_GB_en_GB @Transactional_LE_GB_en_GB
@Regression_DS_GB_en_GB @Regression_LE_GB_en_GB
Scenario: Checkout With Maestro
Given an guest user
When accessing the homepage 
And adding "1" product to the shopping bag
And accessing the bag page
And accessing the checkout page
And enter the shipping information: name "Martin", surname "QALevis", address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
And enter the contact information: email "martinqalevis@gmail.com" and phone "shipping.telephone"
And check the age restriction
And submit the information
And choose Maestro credit card
And enter the credit card number "5641820000000005", the security code "111", the expiration month "01" and expiration year "16"
Then I should get the order placed and see the order confirmation page for email "MARTINQALEVIS@GMAIL.COM"
And access to Global Collect
And I should see the order in Global Collect
And also the "MAESTRO" payment