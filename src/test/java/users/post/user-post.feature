Feature: Post user on reqres

  Background:
    * url "https://reqres.in"
    * path "/api/users"
    * request { "name": "#(name)", "job": "#(job)" }

  Scenario Outline: Post a user
    When method post
    Then status 201

    Examples:
      | name    | job       |
      | jairo   | leader QA |
      | enrique | doctor    |
      | anna    | teacher   |


  Scenario: Post a user without job
    And request { "name": "jairo"}
    When method post
    Then status 201

  Scenario: Post a user with name invalid
    And request { "name": "$%&%", "job": "QA software" }
    When method post
    Then status 201
    #Este usuario se esta creando con caracteres especiales, se debe mejorar las validaciones del endpoint