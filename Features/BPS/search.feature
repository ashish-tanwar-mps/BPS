Feature: To test the functionality of Search in BPS

Background:  
           Given user lands on BPS homepage

Scenario Outline: Verification of Search
           
           Then user enter search term "<searchterm>"
           Then user click on search button
           Then verify search result title with search term "<searchterm>"

Examples: 
|searchterm|
|In what ways will AI enhance psychometric testing in the workplace?|

