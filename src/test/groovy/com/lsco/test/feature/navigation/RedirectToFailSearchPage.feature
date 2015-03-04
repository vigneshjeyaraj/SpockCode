#SPRING-17931
Feature: Redirected to the Fail Search Page , when the user has placed an invalid search.
  @NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
  @Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
  Scenario: Redirected to the Fail Search Page
    Given an guest user
    When accessing the homepage
    And searching a random term with length 50
    And I should be redirected to the Fail Search Page.
    Then I see the Other Items you migth like section with products quantity more than 0




