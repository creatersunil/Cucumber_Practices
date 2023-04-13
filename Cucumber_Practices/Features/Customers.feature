#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

#@tag
Feature: Customers

 Background: Below are the common steps for each scenario
		Given User Launch Chrome browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then User can view Dashboad
    When User click on customers Menu
    And click on customers Menu Item
@sanity
Scenario: Add new Customer
    And click on Add new button
    Then User can view Add new customer page
    When User enter customer info
    And click on Save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser 
@regre
Scenario: Search customer by EMailID
    And Enter customer EMail
    When click on search button
    Then User should found Email in the search table
    And close browser
@regre
Scenario: Search customer by Name
    And Enter customer FirstName
    And Enter customer LastName
    When click on search button
    Then User should found Name in the search table
    And close browser

# @tag2
# Scenario Outline: Title of your scenario outline
#   Given I want to write a step with <name>
#  When I check for the <value> in step
#  Then I verify the <status> in step

#  Examples: 
#     | name  | value | status  |
#     | name1 |     5 | success |
#      | name2 |     7 | Fail    |
