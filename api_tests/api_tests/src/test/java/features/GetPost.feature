Feature:
  Verify different GET operations with REST - assured
  Scenario:
    Verify one that all users have names
    Given I perform GET operation for "url"
    And I GET for "/users"
    Then all users should have a name
    And All users have emails
    And  all users have username
    And all emails should be valid
    And Company Catchphrases must have less than 50 characters
