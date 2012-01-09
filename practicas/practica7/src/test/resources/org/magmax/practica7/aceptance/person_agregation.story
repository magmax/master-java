Scenario: Add a new person
Given no database file
When name is setted to 'Miguel'
And phone is setted to '555666777'
And DNI is setted to '12345678X'
And somebody press on agregate button
Then the person Miguel/555666777/12345678X is added

Scenario: Add a second person with same DNI
Given a clean datafile
And added the user Miguel/555666777/12345678Y
When name is setted to 'Pepe'
And phone is setted to '12345678'
And DNI is setted to '12345678Y'
And somebody press on agregate button
Then the exception 'The DNI 12345678Y already exists.' is launched

