@tag
Feature: Error Validation
  I want to use this template for my feature file

	@ErrorValidation
  Scenario Outline: Login with wrong password
    Given I am on landing page
    When logged in with email <email> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | email | password | productName |
      | chocoking@gmail.com | @Bc2345 | ZARA COAT 3 |
