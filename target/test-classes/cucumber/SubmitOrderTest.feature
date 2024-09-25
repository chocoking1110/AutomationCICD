@tag
Feature: Purchase Order from ecommerce
  I want to use this template for my feature file

	Background: 
	Given I am on landing page
	
	@Regression
  Scenario Outline: Positive case
    Given logged in with email <email> and password <password>
    When I add product <productName> to cart 
    And I checkout productName <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | email | password | productName |
      | chocoking@gmail.com | @Bc23456 | ZARA COAT 3 |
