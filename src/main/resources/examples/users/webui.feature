Feature: sample karate webui test script
  for help, see: https://github.com/intuit/karate/wiki/IDE-Support

  @webui
  Scenario: Sample Web UI test
    Given driver 'https://github.com/login'
    And driver.input('#login_field', 'abc@gmail.com')
    And driver.input('#password', '1')
    When driver.click("input[name=commit]")
    * driver.screenshot()
    Then match html('#js-flash-container') contains 'Incorrect username or password.'
