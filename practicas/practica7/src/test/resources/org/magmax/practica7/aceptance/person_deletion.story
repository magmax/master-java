Scenario: Deleting a person from database
Given a person 'Miguel/555666777/12345678Y'
When somebody press on show names button
And somebody doubleclicks on 'Miguel' with dni '12345678Y'
Then person 12345678Y is not in database.

Scenario: Two people with same name
Given a person 'Miguel/555666777/12345678A'
And a person 'Miguel/555666777/12345678B'
When somebody press on show names button
And somebody doubleclicks on 'Miguel' with dni '12345678A'
Then person 12345678A is not in database
And person 12345678B is in database