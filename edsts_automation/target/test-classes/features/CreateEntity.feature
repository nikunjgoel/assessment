Feature: Create Entity View And Upload functionality

  @CreateEntity
  Scenario Outline: To verify the date and time of delievery and Review page
    Given User is on Home Page "<TestCaseName>" and "<TestSheetName>"
    #When User Login with "DO+DP+DU" Credentials
    Then user Enter product in search details page
    Then user Click search button in search details page
    Then user verify search result displayed in list and click the search data
    Then user add selected item to checklist from result page
    Then user checkout the selected item from checkout page
    Then user enter delivery details in delivery page
    And user verify the Delivery date and time on the Review and pay page.
    And quit from the application

    Examples: 
      | TestCaseName | TestSheetName |
      | SearchData   | UATSanity     |

  @CreateEntity1
  Scenario Outline: To verify the OAuth pattern of an API
    Given User is on Home Page "<TestCaseName>" and "<TestSheetName>"
    Then user get access token using ouath in rest details page
    And user verify the get API using checkUserInfoContainsUserId
    And user verify the get API using checkNumberOfAssociatedPaymentsIsEqualToZero
    #And user verify basic authentication
    And quit from the application

    Examples: 
      | TestCaseName | TestSheetName |
      | APITest      | UATSanity     |
