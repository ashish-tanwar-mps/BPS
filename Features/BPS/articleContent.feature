Feature: To test the functionality of Article Content page

Background:  
            Given user lands on BPS homepage

Scenario Outline: Verification of Article content page with successful login

            Then user accept the cookies
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
            
            Then verify Add to Favourites action tool bar "<searchterm>"
            Then verify manage your favourites section "<searchterm>"
            Then verify remove from favourites section "<searchterm>"
            
            Then verify cite button "<searchterm>"
            
            Then verify share button 
            Then verify share social icon "<facebook>""<facebookid>"
            Then verify share social icon "<twitter>""<twitterid>"
            Then verify share social icon "<linkedin>""<linkedinid>" 
            Then close share modal
            
            Then verify create alert functionality
            Then verify manage alert functionality
            Then verify permissions button functionality "<permissions>""<copypermissiontext>"
            
            Then verify focus view functionality "<exitfocustext>"
            Then verify next and previous link "<searchterm>""<searchterm2>"
            
            Then verify Related content "<rck1>""<rck2>""<rck3>""<rck4>""<rck5>"
            Then verify keywords content "<k1>""<k2>""<k3>""<k4>""<k5>""<k6>""<k7>""<k8>""<k9>""<k10>"
            Then verify Table of content section "<ktc1>""<ktc2>""<ktc3>""<ktc4>""<ktc5>""<ktc6>""<ktc7>"
            
            Then verify Access this content pane
            Then close the driver
            
           
           
Examples: 
|searchterm|title|username|password|authorname|doilink|facebook|facebookid|twitter|twitterid|linkedin|linkedinid|permissions|copypermissionstext|exitfocustext|searchterm2|k1|k2|k3|k4|k5|k6|k7|k8|k9|k10|ktc1|ktc2|ktc3|ktc4|ktc5|ktc6|ktc7|rck1|rck2|rck3|rck4|rck5|
|In what ways will AI enhance psychometric testing in the workplace?|Sign in to Highwire logon|Elisa Legend|balloons2|Herbert, Niamh|https://doi.org/10.53841/bpsadm.2024.16.1.24|facebook|448676|twitter|448677|linkedin|448678|permissions|Copies and permissions|Exit focus|Artificially disinformed and radicalised: How AI produced disinformation could encourage radicalisation|AI|Psychometric testing|Efficiency|Accuracy|Fairness|Machine learning|Data analytics|Candidate selection|Talent management|Employee development|Abstract|Introduction|Enhancing psychometric testing in the workplace|The risks|Important considerations|Conclusion|References|The fair and appropriate testing of disabled candidates using psychological testing|Motivation questionnaire|Candidate reported usage and opinion of psychometric testing in the UK recruitment market|Talent management and managing a continuing professional development session|10x Personality|                                 
