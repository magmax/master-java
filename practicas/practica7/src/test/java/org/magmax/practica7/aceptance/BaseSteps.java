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

import java.sql.SQLException;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.magmax.practica7.persistence.Persistence;
import org.magmax.practica7.pojo.Person;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class BaseSteps {

    protected Exception exception;
    protected Person person;
    protected Persistence persistence;
    protected boolean inMemory = true;

    @BeforeScenario
    public void setupStory() {
        person = new Person();
        exception = null;
        persistence = new Persistence(inMemory);
    }

    @AfterScenario
    public void tearDownStory() throws SQLException {
        persistence.clear();
        persistence.close();
    }

    @Given("no database file")
    @Alias("a clean datafile")
    public void givenNoDatabaseFile() {
        // Already done
    }

    @When("name is setted to '$name'")
    public void whenNameIsSetted(String name) {
        person.setName(name);
    }

    @When("phone is setted to '$phone'")
    public void whenPhoneIsSetted(String phone) {
        person.setPhone(phone);
    }

    @When("DNI is setted to '$dni'")
    public void whenDNIIsSetted(String dni) {
        person.setDni(dni);
    }

    @Then("the exception '$message' is launched")
    public void thenExceptionIsLaunched(String message) {
        assertNotNull("No exceptions found", exception);
        assertEquals(message, exception.getMessage());
    }
}