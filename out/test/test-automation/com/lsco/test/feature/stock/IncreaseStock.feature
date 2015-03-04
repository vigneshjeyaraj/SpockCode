Feature: Increase Stock

@Transactional_LE_GB_en_GB @Transactional_LE_DE_de_DE @Transactional_DS_GB_en_GB @Transactional_DS_DE_de_DE
@Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
Scenario: Increase Stock
Given access to Hybris Administration Console (username "levi.team", password = "pass1234")
And run the groovy script "product/stockGroovyScript.txt"
