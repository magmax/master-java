Scenario: Deleting a person from database
Given a database with user 'Miguel/555666777/12345678Y'
When somebody press on show names button
And somebody doubleclick on name 'Miguel'
Then person 12345678Y is not in database.
