Feature: Browse Category Pages

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Browse Category Pages
Given an guest user 
When accessing the first catalog and the first category page
Then I should see the products information and images. 
