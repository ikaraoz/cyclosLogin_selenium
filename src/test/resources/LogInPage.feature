Feature: CyclosBank login page
  Scenario: The user can login with a valid username and password
    Given the user navigates to "https://demo.cyclos.org/#login"
    When the user enters "demo" to "principal"
    And the user enters "1234" to "password"
    And the user clicks "//*[@class='actionButton']"
    Then the user views "Welcome to the Cyclos4 Demo"

Scenario Outline: The user cannot login with an invalid username and/or password
  Given the user navigates to "https://demo.cyclos.org/#login"
  When the user enters "<username>" to "principal"
  And the user enters "<password>" to "password"
  And the user clicks "//*[@class='actionButton']"
  Then the user views errorMessage "<errorMessage>"
  Examples:
    | username | password | errorMessage                                                |
    #| demo     | 12345    | The given name / password are incorrect. Please, try again. |
    #| demooo   | 1234     | The given name / password are incorrect. Please, try again. |
    | demo     |          | Password is required                                        |
    |          | 1234     | Login name is required                                      |





