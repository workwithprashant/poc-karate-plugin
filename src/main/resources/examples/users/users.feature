Feature: sample karate test script
  for help, see: https://github.com/intuit/karate/wiki/IDE-Support

  Background:
    * url 'https://jsonplaceholder.typicode.com'

  @demo
  Scenario: create a user and then get it by id
    * def response = call read('library.feature@getusers')
    * print response[0]
    * def user =
      """
      {
        "name": "Test User",
        "username": "testuser",
        "email": "test@user.com",
        "address": {
          "street": "Has No Name",
          "suite": "Apt. 123",
          "city": "Electri",
          "zipcode": "54321-6789"
        }
      }
      """

    Given url 'https://jsonplaceholder.typicode.com/users'
    And request user
    When method post
    Then status 201

    * def id = response.id
    * print 'created id is: ', id