#SPRING-15529
Feature: Verify that certain data is populated within the Search Result page

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Populate the Search Result page
Given an guest user 
When accessing the homepage
And searching the term "men" 
Then the search page is populated with "men" result.
