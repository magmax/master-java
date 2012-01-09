Scenario: Searching a person in database
Given a database with user Miguel/555666777/12345678Y
When somebody writes '12345678Y' in the search box
And press on Search button
Then person Miguel/555666777/12345678Y is returned

Scenario: Exception is returned if not found
Given a database with user Miguel/555666777/12345678Z
When somebody writes 'abcdefg' in the search box
And press on Search button
Then the exception 'DNI abcdef was not found.' is launched
