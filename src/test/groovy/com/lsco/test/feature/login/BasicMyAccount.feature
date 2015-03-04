#SPRING-15521
Feature: Login into My Account

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Login into My Account using EMail and Password
Given an guest user 
When accessing the homepage
And accessing the login page
And enter the email "qa2014testautomation@mailinator.com" and password "123456Mb" 
Then I should see the my account page with "JOHN QA" displayed in the header. 
