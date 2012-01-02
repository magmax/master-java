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

import java.io.File;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.magmax.practica7.pojo.Person;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class PersonAgregationSteps {

    private Person person = null;
    private static final String FILENAME = "database.dat";

    @BeforeStory
    public void setupStory() {
        person = new Person();
    }

    @Given("no database file")
    public void givenNoDatabaseFile() {
        File file = new File(FILENAME);
        file.delete();
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

    @When("somebody press on agregate button")
    @Pending
    public void whenSomebodyPressOnAgregateButton() {
        // PENDING
    }

    @Then("the person Miguel/555666777/12345678X is added")
    public void thenThePersonMiguelIsAdded() {
        // PENDING
    }
}
