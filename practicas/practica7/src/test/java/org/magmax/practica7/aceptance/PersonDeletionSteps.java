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

    @Given("a person '$name/$phone/$dni'")
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

    @When("somebody doubleclicks on '$name' with dni '$dni'")
    public void whenThenSomebodyDoubleclickOnName(String name, String dni) throws SQLException, DatabaseNotDefinedException {
        Person person = new Person();
        person.setName(name);
        person.setDni(dni);
        persistence.delete(person);
    }

    @Then("person $dni is not in database.")
    public void thenPersonIsNotInDatabase(String dni) throws SQLException, DatabaseNotDefinedException {
        for (Person each : persistence.retrievePersons()) {
            if (each.getDni().equals(dni)) {
                fail(String.format("Person %s already exists", dni));
            }
        }
    }

    @Then("person $dni is not in database.")
    public void thenPersonIsInDatabase(String dni) throws SQLException, DatabaseNotDefinedException {
        for (Person each : persistence.retrievePersons()) {
            if (each.getDni().equals(dni)) {
                return;
            }
        }
        fail(String.format("Person %s not exists", dni));
    }
}
