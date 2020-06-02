Feature: CyclosBank login page
  Scenario: The user can login with a valid username and password
    Given the user navigates to "https://demo.cyclos.org/#login"
    When the user enters "demo" to "principal"
    And the user enters "1234" to "password"
    And the user clicks "//*[@class='actionButton']"
    Then the user views "Welcome to the Cyclos4 Demo"

