Feature: Login Functionality

  Scenario: Successful Login
    Given user is in myloft page
    When User enters valid email address "<email>" and the password "<password>"
    And User able to click Login
    Then User should be logged in successfully
    
    And User able to select an Institute "<institute>"
    And User able to click on "App settings" from the left panel
    Then Verify the number of links available
    When User click the "Add New" button
    And User should able to enter the title "<title>" and URL "<NewUrlAdd>" and save the link
    Then Verify the added link appears at the end of the list
        
    When User delete the newly added link
    Then Verify the link is deleted successfully
    

