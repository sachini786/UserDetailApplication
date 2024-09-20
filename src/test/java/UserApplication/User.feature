Feature:Test the User API

  Scenario:Get user details
    Given url 'http://localhost:8080/api/v1/User'
    When method GET
    Then status 200
    And match response == '#[]'
    * print 'Response:', response

  Scenario:Get user details with valid userID
    Given url 'http://localhost:8080/api/v1/User/1'
    When method GET
    Then status 200
    And match response == { userID: 1, firstName: '#string', lastName: '#string', dateOfBirth: '#string', email: '#string', address: '#string', age: '#number', phoneNumber: '#string' }
    * print 'Response:', response


  Scenario: Get user details with an invalid userID
    Given url 'http://localhost:8080/api/v1/User/70'
    When method GET
    Then status 404
    And match response contains { status: 404, error: 'Not Found' }

#  Scenario: Add a valid user
#    Given url 'http://localhost:8080/api/v1/User/User'
#    And request {"firstName": "Rashmi","lastName": "Fernando","dateOfBirth": "1990-09-11","address": "n0 30,warakagoda,Gampaha","email": "RashmiF@gmail.com","age": 34,"phoneNumber": "091-2341678"}
#    When method POST
#    Then status 201

  Scenario: Add a user without mandatory Filed - firstname and  valid value for other field
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"lastName": "Pramudi", "dateOfBirth": "11-07-2000", "address": "n0 1,kandy","email": "Pramudi@gmail.com", "age": 24, "phoneNumber": "071-2341678"}
    When method POST
    Then status 400


  Scenario: Add a user without mandatory Filed- lastName and  valid value for other field
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName": "chaly", "dateOfBirth": "09-08-2000", "address": "no 21,galle","email": "chalyD@gmail.com", "age": 23, "phoneNumber": "071-1244167"}
    When method POST
    Then status 400

  Scenario: Add a user without mandatory Filed- dateOfBirt and  valid value for other field
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName": "John","lastName": "Merious", "address": "no 21,galle road,colombo","email": "John@gmail.com", "age": 22, "phoneNumber": "072-1244167"}
    When method POST
    Then status 400

  Scenario: Add a user without mandatory Filed- address and  valid value for other field
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName": "Ridma","lastName": "Perera","dateOfBirth": "2001-01-01","email": "Ridma@gmail.com", "age": 23, "phoneNumber": "072-3416782"}
    When method POST
    Then status 400

  Scenario: Add a user without mandatory Filed - email and valid value for other field
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName": "Neha","lastName": "Fernando","dateOfBirth": "2000-01-01","address": "no 23 ,galle road,colombo", "age": 24, "phoneNumber": "062-3416782"}
    When method POST
    Then status 400

  Scenario: Add a user without mandatory Filed - phoneNumber and valid value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName": "Prasad","lastName": "Sharmal","dateOfBirth": "2005-10-01","address": "no 03 ,colombo","email": "prasad@gmail.com", "age": 24}
    When method POST
    Then status 400

#  Scenario: Add a user without a age and valid value for other fields
#    Given url 'http://localhost:8080/api/v1/User/User'
#    And request {"firstName": "Prasad","lastName": "Sharmal","dateOfBirth": "04/01/2000","address": "no 03 ,colombo","email": "prasad@gmail.com","phoneNumber": "062-3416782"}
#    When method POST
#    Then status 201
#
  Scenario: Add a  user with Invalid firstName and valid value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName":1234, "lastName": "Fernando", "dateOfBirth": "1990-09-11", "address": "n0 30,walapane","email": "Achala5@gmail.com", "age": 24, "phoneNumber": "081-2341678"}
    When method POST
    Then status 400

  Scenario: Add a  user with Invalid lastName and value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName":"Achala", "lastName": 123, "dateOfBirth": "09/11/1999", "address": "n0 30,walapane","email": "achala725@gmail.com", "age": 24, "phoneNumber": "081-2341678"}
    When method POST
    Then status 400

  Scenario: Add a  user with Invalid Date format of  dateOfBirth and value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName":"Devdi", "lastName": "Gamage", "dateOfBirth": "1999-01-08", "address": "n0 30,walapane","email": "devdi@gmail.com", "age": 25, "phoneNumber": "081-2341078"}
    When method POST
    Then status 400

#  Scenario: Add a  user with Invalid Date dateOfBirth and value for other fields
#    Given url 'http://localhost:8080/api/v1/User/User'
#    And request {"firstName":"Devdi", "lastName": "Gamage", "dateOfBirth": "31/02/1999", "address": "n0 30,walapane","email": "devdi@gmail.com", "age": 25, "phoneNumber": "081-2341078"}
#    When method POST
#    Then status 400
#


  Scenario: Add a  user with Invalid address and value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName":"Harsha", "lastName": "Jayalath", "dateOfBirth": "01/02/2000", "address": 12@34,"email": "Harshaj@gmail.com", "age": 24, "phoneNumber": "081-4126785"}
    When method POST
    Then status 400

  Scenario: Add a  user with Invalid address and value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName":"Harsha", "lastName": "Jayalath", "dateOfBirth": "01/02/2000", "address": 12@34,"email": "Harshaj@gmail.com", "age": 24, "phoneNumber": "081-4126785"}
    When method POST
    Then status 400

  Scenario: Add a  user with Invalid email
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName": "Masha","lastName": "nanayakkara","dateOfBirth": "03/02/1999","address": "no 27,watagoda,Hp","email":1234,"age": 26,"phoneNumber": "0812307035"}
    When method POST
    Then status 400

  Scenario: Add a  user with Invalid age and value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName":"Maya", "lastName": "Gamlath", "dateOfBirth": "01/02/2000", "address":"No 10/1,Ganemulla","email": "mahaj@gmail.com", "age": "Twenty-four", "phoneNumber": "071-4126785"}
    When method POST
    Then status 400


  Scenario: Add a  user with Invalid phone number and value for other fields
    Given url 'http://localhost:8080/api/v1/User/User'
    And request {"firstName":"Randimu", "lastName": "Fonseka", "dateOfBirth": "01/07/2000", "address":"No 10/1,Ganemulla","email": "randinu@gmail.com", "age": 24, "phoneNumber":0912467 }
    When method POST
    Then status 400

#  Scenario: Delete user with ID 9
#    Given url 'http://localhost:8080/api/v1/User/9'
#    When method DELETE
#    Then status 200
#    * print 'User with ID 9 has been deleted'
