#SPRING-18058
Feature: Order WIth Gift Wrap

Scenario: Order WIth Gift Wrap
Given a registered user
When accessing the homepage
And accessing the login page
And enter the email "qa2014testautomation@mailinator.com" and password "123456Mb"
And I should see the my account page with "JOHN QA" displayed in the header. 
And adding "1" product to the shopping bag
And accessing the bag page
And accessing the checkout page
And enter the shipping information: name "Martin", surname "QALevis", address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
And enter the contact information: email "qa2014testautomation@mailinator.com" and phone "shipping.telephone"
And check the gift wrap option
And submit the information
And choose MasterCard credit card
And enter the credit card number "5555555555554444", the security code "111", the expiration month "01" and expiration year "16"
Then I should get the order placed and see the order confirmation page for email "QA2014TESTAUTOMATION@MAILINATOR.COM"
And accessing the homepage
And accessing the login page
And enter the email "qa2014testautomation@mailinator.com" and password "123456Mb"
And I should see the my account page with "JOHN QA" displayed in the header. 
And accesing the last placed order in the customer order history
And I should see the gift wrap information displayed
