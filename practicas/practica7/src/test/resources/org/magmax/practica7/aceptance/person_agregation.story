Scenario: Add a new person
Given no database file
When name is setted to 'Miguel'
And phone is setted to '555666777'
And DNI is setted to '12345678X'
And somebody press on agregate button
Then the person Miguel/555666777/12345678X is added