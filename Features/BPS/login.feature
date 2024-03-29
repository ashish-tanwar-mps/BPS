Feature: To test the functionality of Login BPS

Scenario Outline: Verification of Login Functionality for BPS

   Given user lands on BPS homepage
   Then user click and verify Sign In button with "<title>"
   Then user enters username "<username>"
   Then user enters password "<password>"
   Then user click and verify Sign In button with valid credentials
   
   Examples: 
   |title|username|password|
   |Sign in to BPS Website|xyz|123|
 