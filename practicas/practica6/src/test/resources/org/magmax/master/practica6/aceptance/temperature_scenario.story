Story: retrieve temperature
As a client
I would like retrieve temperatures
So that I can test it

Scenario: Retrieving no temperature
Given an empty configuration file
And after launching the Server
When the client ask the temperature for TOLEDO
Then it returns no temperature

Scenario: Retrieving some temperatures
Given a valid configuration file
And after launching the Server
When the client ask the temperature for TOLEDO
Then it returns [21.1, 22.2, 13.0]
