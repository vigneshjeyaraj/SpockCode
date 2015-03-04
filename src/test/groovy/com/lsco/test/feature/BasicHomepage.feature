Feature: Access the Homepage

@Basic_LE_GB_en_GB @Basic_LE_DE_de_DE @Basic_DS_GB_en_GB @Basic_DS_DE_de_DE
@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Accessing the Homepage
Given an guest user 
When accessing the homepage 
Then i am able to see the homepage