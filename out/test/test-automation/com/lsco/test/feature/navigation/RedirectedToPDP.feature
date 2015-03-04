#SPRING-15524 (levi/GB/DE)
Feature: Redirected to the PDP of a specific product, when selecting it on the Women-Jeans Category page.

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE
Scenario: Redirected to the PDP of a specific product
Given an guest user 
When accessing the women jeans category page
And appling filters
And selecting a product from grid
Then I should be redirected to the corresponing PDP. 
