#SPRING-17766 (levi/dockers/GB/DE)
Feature: Verify adding to the bag from PDP

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Verify adding to the bag from PDP
Given an guest user
When accessing the homepage
And adding "2" product to the shopping bag
Then I should see "2" product in the shopping bag.
