#SPRING-15525 (levi/dockers/GB/DE)
Feature: Shopping bag access

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Shopping bag access
Given an guest user
When accessing the homepage
And adding "1" product to the shopping bag
Then I should see "1" product in the shopping bag.
