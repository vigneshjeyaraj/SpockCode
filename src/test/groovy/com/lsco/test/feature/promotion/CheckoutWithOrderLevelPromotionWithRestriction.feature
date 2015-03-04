#SPRING-17191
Feature: Checkout with order level promotion with restriction

Background:
Given access to Hybris Administration Console (username "levi.team", password = "pass1234")
And run the groovy script "promotion/disablePromotionGroovyScript.txt"
And run the groovy script "promotion/enableAUTO-LS-OR-threshold-fixed-discount-ID-17191.txt"

@Transactional_LE_GB_en_GB @Transactional_LE_DE_de_DE @Transactional_DS_GB_en_GB @Transactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Checkout with order level promotion with restriction
Given an guest user
When creating a new email in "https://www.mailinator.com" with domain "@mailinator.com"
And accessing the homepage
When accessing some product detail page
And adding the product to the shopping bag
And accessing the bag page
And apply the promo coupon "AUTO-COUPON-17191" on the shopping bag
And accessing the checkout page
And enter the shipping information: name "Martin", surname "QALevis", address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
And enter the contact information: email and phone "shipping.telephone"
And check the age restriction
And submit the information
And choose Visa credit card
And enter the credit card number "4111111111111111", the security code "111", the expiration month "01" and expiration year "16"
Then I should get the order placed and see the order confirmation page for email
And with the discounts "5.00"
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And the order is created in Hybris with the same total amount
And access to Global Collect
And I should see the order in Global Collect
And receive the order confirmation email in "https://www.mailinator.com" with the discounts "5.00"
