/*
 * Copyright (C) 2012 miguel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.magmax.practica7.aceptance;

import java.sql.SQLException;
import java.util.Arrays;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.magmax.practica7.exceptions.DatabaseNotDefinedException;
import org.magmax.practica7.pojo.Person;
import static org.junit.Assert.*;

/**
 *
 * @author miguel
 */
public class PersonDeletionSteps extends BaseSteps {
    private String personName;

    @Given("a database with user '$name/$phone/$dni'")
    public void givenADatabaseWithUser(String name, String phone, String dni) throws Exception {
        Person thePerson = new Person();
        thePerson.setName(name);
        thePerson.setPhone(phone);
        thePerson.setDni(dni);
        persistence.create(thePerson);
    }

    @When("somebody press on show names button")
    public void whenSomebodyPressOnShowNamesButton() {
        // Do nothing
    }

    @When("somebody doubleclick on name '$name'")
    public void whenThenSomebodyDoubleclickOnName(String name) throws SQLException, DatabaseNotDefinedException {
        personName = name;
        persistence.delete(name);
    }

    @Then("person 12345678Y is not in database.")
    public void thenPerson12345678YIsNotInDatabase() throws SQLException, DatabaseNotDefinedException {
        for (String each : persistence.showNames()) {
            if (each.equals(personName)) {
                fail(String.format("Person %s already exists", personName));
            }
        }
    }
}
