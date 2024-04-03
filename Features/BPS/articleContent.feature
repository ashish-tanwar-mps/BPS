Feature: To test the functionality of Article Content page

Background:  
            Given user lands on BPS homepage

Scenario Outline: Verification of Article content page with successful login

            Then user click and verify Sign In button with "<title>"
            Then user enters username "<username>"
            Then user enters password "<password>"
            Then user click Sign In button with valid credentials
            
            Then user enter search term "<searchterm>"
            Then user click on search button
            Then verify search result title with search term "<searchterm>"
            Then user click on searched article "<searchterm>"
            
            Then verify article content title with search term "<searchterm>"
            Then verify author "<authorname>"
            Then verify article DOI link "<doilink>"
           
Examples: 
|searchterm|title|username|password|authorname|doilink|
|In what ways will AI enhance psychometric testing in the workplace?|Sign in to Highwire logon|Elisa Legend|balloons2|Herbert, Niamh|https://doi.org/10.53841/bpsadm.2024.16.1.24|
