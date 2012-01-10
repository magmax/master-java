Scenario: Show a list of people
Given a clean datafile
And added the user Miguel/555666777/12345678V
And added the user Juan/555666778/12345678Z
And added the user Paco/555666779/12345678Y
When somebody press on show names button
Then the list [Juan,Miguel,Paco] is returned

