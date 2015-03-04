#SPRING-18082
Feature: Checkout as a registered user logged in on the Checkout page

Scenario: Checkout as a registered user logged in on the Checkout page
Given an guest user 
When accessing the homepage
And adding "1" product to the shopping bag
And accessing the bag page
And accessing the checkout page
And login on checkout with username "qa2014testautomation@mailinator.com" and password "123456Mb"
And enter the shipping information: name "Martin", surname "QALevis", address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
And enter the contact information: email "qa2014testautomation@mailinator.com" and phone "shipping.telephone"
And submit the information
And choose Amex credit card
And enter the credit card number "378282246310005", the security code "1111", the expiration month "01" and expiration year "16"
Then I should get the order placed and see the order confirmation page for email "QA2014TESTAUTOMATION@MAILINATOR.COM"
And accessing to customer order history and see the order placed
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And the order is created in Hybris with the same total amount
And access to Global Collect
And I should see the order in Global Collect
And also the "AMERICAN" payment with the same total amount
And receive the order confirmation email in "https://www.mailinator.com" with username "qa2014testautomation"