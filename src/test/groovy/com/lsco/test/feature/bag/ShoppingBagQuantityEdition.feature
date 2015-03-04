#SPRING-15526 (levi/dockers/GB/DE)
Feature: Shopping Bag Quantity Edition
@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Shopping Bag Quantity Edition
Given an guest user
When accessing the homepage 
And adding "1" product to the shopping bag
And accesing the shopping bag page
Then I should be able to change the product quantity
