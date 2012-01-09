/*
 * Copyright (C) 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.magmax.practica7.pojo.Person;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class PersonSearchSteps extends BaseSteps{

    private String searchbox = "";
  
    @Given("a database with user $name/$phone/$dni")
    public void givenADatabaseWithUser(String name, String phone, String dni) throws Exception {
        Person person = new Person();
        person.setName(name);
        person.setPhone(phone);
        person.setDni(dni);
        persistence.create(person);
    }

    @When("somebody writes '$dni' in the search box")
    public void whenSomebodyWritesADniInTheSearchBox(String dni) {
        searchbox = dni;
    }

    @When("press on Search button")
    public void whenPressOnSearchButton() {
        try {
            person = persistence.retrievePerson(searchbox);
        } catch (Exception ex) {
            this.exception = ex;
        }
    }

    @Then("person $name/$phone/$dni is returned")
    public void thenPersonIsReturned(String name, String phone, String dni) {
        assertNull("There was an unexpected exception", exception);
        assertNotNull("No person to compare with!");
        assertEquals(name, person.getName());
        assertEquals(phone, person.getPhone());
        assertEquals(dni, person.getDni());
    }

    @Then("the exception '$message' is launched")
    public void thenTheExceptionDNIAbcdefWasNotFoundIsLaunched(String message) {
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
}
