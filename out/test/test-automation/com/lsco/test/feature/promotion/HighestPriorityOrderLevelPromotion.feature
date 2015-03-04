#SPRING-17194
Feature: Highest priority order level promotion displayed in the buy stack

Background:
Given access to Hybris Administration Console (username "levi.team", password = "pass1234")
And run the groovy script "promotion/disablePromotionGroovyScript.txt"
And run the groovy script "promotion/enableAUTO-LS-OR-threshold-fixed-discount-ID-17194-HIGH.txt"

@NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Highest priority order level promotion displayed in the buy stack
Given an guest user
When accessing some product detail page
Then I should see the highest priority order level promotion displayed in the buy stack
 
