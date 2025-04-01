@tag
Feature: Purches the order from Ecommerce Website
  I want to use this template for my feature file


  Background:
  Given I landed on Ecommerce Page

@tag2
Scenario Outline: Positive Test of Submitting the Order

    Given Logged in with username <userName> and password <password>
    When I add product <productName> to cart
    And I check out <productName> and submit the order
   Then "THANK YOU FOR THE ORDER." message is displayed on ConfirmationPage


    Examples: 
      |userName                 |password       |productName         |
      |sourabhbhosale@gmail.com |Sourabh@99     |ADIDAS ORIGINAL     |
