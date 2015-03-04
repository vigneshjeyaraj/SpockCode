#SPRING-15527
Feature: Checkout As Logged User

Scenario: Checkout As Logged User
Given a registered user
When accessing the homepage
And accessing the login page
And enter the email "qa2014testautomation@mailinator.com" and password "123456Mb"
And I should see the my account page with "JOHN QA" displayed in the header.  
And adding "1" product to the shopping bag
And accessing the bag page
And accessing the checkout page
And enter the shipping information: name "Martin", surname "QA", address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
And enter the contact information: email "qa2014testautomation@mailinator.com" and phone "shipping.telephone"
And submit the information
And enter the paypal username "paypal.username" and the password "paypal.password"
Then I should get the order placed and see the order confirmation page for email "QA2014TESTAUTOMATION@MAILINATOR.COM"
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And the order is created in Hybris with the same total amount
And receive the order confirmation email in "https://www.mailinator.com" with username "qa2014testautomation"
