#SPRING-17193 (levi/dockers/GB/DE)
Feature: Checkout with product level promotion

Background:
Given access to Hybris Administration Console (username "levi.team", password = "pass1234")
And run the groovy script "promotion/disablePromotionGroovyScript.txt"
And run the groovy script "promotion/enableAUTO-LS-PR-level-promotion-ID-17193.txt"

@Transactional_LE_GB_en_GB @Transactional_LE_DE_de_DE @Transactional_DS_GB_en_GB @Transactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Checkout with product level promotion
Given an guest user
When accessing the homepage
And accesing a product detail page
And adding the product to the shopping bag
And accessing the bag page
And accessing the checkout page
And enter the shipping information: name "Martin", surname "QALevis", address "shipping.address", city "shipping.city" and postcode "shipping.postcode"
And enter the contact information: email "martinqalevis@gmail.com" and phone "shipping.telephone"
And check the age restriction
And submit the information
And choose Visa credit card
And enter the credit card number "4111111111111111", the security code "111", the expiration month "01" and expiration year "16"
Then I should get the order placed and see the order confirmation page for email "MARTINQALEVIS@GMAIL.COM"
And with the corresponding discounts
And access to Global Collect
And I should see the order in Global Collect
