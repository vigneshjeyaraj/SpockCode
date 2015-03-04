#SPRING-17767
Feature: Verify that items displayed within "GOES WELL WITH" section on PDP

@NonTransactional_LE_GB_en_GB @NonTransactional_DS_GB_en_GB
@Regression_LE_GB_en_GB @Regression_DS_GB_en_GB
Scenario: Verify that items displayed within "GOES WELL WITH" section on PDP
Given an guest user
When accessing the homepage
And accessing some product detail page
Then I should see the "goes.well.with.title" section and access to one of the products. 
