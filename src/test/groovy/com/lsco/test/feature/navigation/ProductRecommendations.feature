#SPRING-15528
Feature: Product Recommendations - Verify that items displayed within "YOU MAY ALSO LIKE" section on PDP

@NonTransactional_LE_GB_en_GB @NonTransactional_DS_GB_en_GB
@Regression_LE_GB_en_GB @Regression_DS_GB_en_GB
Scenario: Product Recommendations - Verify that items displayed within "YOU MAY ALSO LIKE" section on PDP
Given an guest user
When accessing the homepage
And accessing some product detail page
Then I should see the "may.like.title.1" or "may.like.title.2" section and access to one of the products. 
 
