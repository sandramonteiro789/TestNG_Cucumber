Feature: Verify the login and register funcationality

  Scenario: Verify if user is able to register to Parabank
    Given User enters the Parabank URL in the browser
    When User clicks on register URL
    Then User enters the user information
      | First Name | Last Name | Address | City | State | Zip Code | Phone | SSN | Username | Password | Confirm |
    And User clicks on register Button

  #Scenario: Verify if user is able to register to Parabank
    #Given User enters the Parabank URL in the browser
    #When User clicks on register URL
    #Then User enters the user information
      #| First Name | Last Name | Address | City | State | Zip Code | Phone | SSN | Username | Password | Confirm |
    #And User clicks on register Button