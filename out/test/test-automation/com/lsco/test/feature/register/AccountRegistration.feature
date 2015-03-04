#SPRING-15520
Feature: Account Registration

@Transactional_LE_GB_en_GB @Transactional_LE_DE_de_DE @Transactional_DS_GB_en_GB @Transactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Register a new account
Given an guest user 
When creating a new email in "https://www.mailinator.com" with domain "@mailinator.com"
And accessing the registration page
And enter the email and passwords "123456Mb" 
Then I should see my new account page with the email displayed in the header
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And see my new acount created in Hybris
And receives the registration confirmation email in "https://www.mailinator.com".
