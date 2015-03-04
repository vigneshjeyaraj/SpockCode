#SPRING-17765 (levi/dockers/GB/DE)
Feature: Shopping Bag SKU Quantity Restriction

  @NonTransactional_LE_GB_en_GB @NonTransactional_LE_DE_de_DE @NonTransactional_DS_GB_en_GB @NonTransactional_DS_DE_de_DE
  @Regression_LE_GB_en_GB @Regression_LE_DE_de_DE @Regression_DS_GB_en_GB @Regression_DS_DE_de_DE
  Scenario: Shopping Bag SKU Quantity Restriction
    Given an guest user
    When accessing the homepage
    And adding "3" product to the shopping bag
    And adding "1" product with the same SKU
    And items in my bag must be "3"
    Then I should be able to see a error message "bag.items.error.exceed.sku.max.quantity"