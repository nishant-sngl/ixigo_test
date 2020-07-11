Functionality:

    the test case return the cheapest n combinations of return flight between two cities.
    
Data: Change the values in config.properties file located in main/java/config folder.

    1. depDateDiff: Departure date 
        number of days from today. Eg. if today is 11july and value of this var is 2, then it will search for 13 july.
    2. retDateDiff: Return date
        number of days from today. Eg. if today is 11july and value of this var is 4, then it will search for 15 july.
    3. resultsCount: number of results.
    4. fromCity: departure city
    5. toCity: arrival city
    
    
Execution:

    run the testng.xml file in the root folder.
    The TC will handle the invalid dates.
    
Env:

    This is tested for following system:
    OS: Mac
    Browser: chrome v83
    
TODO:

    1. The get deal function is not fully optimised. 
        If required, some more time can be spend to think of a optimised solution.
        
Sample Result:

       SpiceJet  SG246, SG988 -+- IndiGo  6E212, 6E218 => 11697
       SpiceJet  SG246, SG988 -+- IndiGo  6E943, 6E5318 => 11697
       SpiceJet  SG246, SG988 -+- IndiGo  6E6613, 6E579 => 11697
       SpiceJet  SG246, SG988 -+- IndiGo  6E906, 6E5318 => 11697
       SpiceJet  SG704, SG988 -+- IndiGo  6E212, 6E218 => 11697
       SpiceJet  SG704, SG988 -+- IndiGo  6E943, 6E5318 => 11697   