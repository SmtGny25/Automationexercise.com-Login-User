
Feature: automationexercise.com - Login User
Background:
  Given I access the Automation Exercise website homepage
  And I should be presented verified home page
  And I click on SingupLogin button

  Scenario: Validate Successful Login With Correct Email and Password
    When I enter the correct mail address
    And I enter the correct password
    And I click to login button
    And I verified that logged in as username
    And I click on Delete Acoount button
    And I click the GoogleAds
    Then I verified that Account Deleted
  Scenario: Validate Unsuccessful Login With Correct Email and Password
    When I enter the uncorrect mail address
    And I enter the uncorrect password
    And I click to login button
    Then I verified error incorrect message
