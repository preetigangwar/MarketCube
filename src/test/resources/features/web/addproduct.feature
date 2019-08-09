Feature:AddProduct

    Background: Login to marketcube page
    Given I am on MarketCube login page
    And I login as "seller"


    Scenario: Add product using form
    Given I am on dashboard page
    When I go to add product page and fill all fields and click on submit
    Then product must be displayed in  product list page


    Scenario: Add product with corrupt image
    Given I am on dashboard page
    When I go to add product page and add a corrupt image in add product page and click on submit
    Then Verification message should be displayed
