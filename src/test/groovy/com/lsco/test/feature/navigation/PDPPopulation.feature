#SPRING-15530
Feature: Verify that certain data is populated within the Product Detail Page

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Verify that certain data is populated within the Product Detail Page
Given an guest user 
When accessing some product detail page
Then I should see the product detail information
And access to Hybris Administration Console (username "levi.team", password = "pass1234")
And I should see the product detail information in Hybris
 