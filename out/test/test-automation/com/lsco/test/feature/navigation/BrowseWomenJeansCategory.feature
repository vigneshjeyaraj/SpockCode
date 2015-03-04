#SPRING-15522 SPRING-15523
Feature: Browse Women Jeans Category Pages

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE
Scenario: Browse Women Jeans Category Pages
Given an guest user 
When accessing the women jeans category page
Then I should see the products information and images of women jeans category. 
