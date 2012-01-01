/*
 * Copyright (C) 2011, 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.master.practica6.aceptance;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbehave.core.annotations.*;
import org.magmax.master.practica6.client.Client;
import org.magmax.master.practica6.configuration.Configuration;
import org.magmax.master.practica6.server.Server;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class RetrieveTemperatureSteps {

    private Float[] temperatures;
    private Server server = null;

    @Given("an empty configuration file")
    public void givenAnEmptyConfigurationFile() {
        Configuration.getInstance().setPersistentFile("InexistentFile");
    }

    @Given("a valid configuration file")
    public void givenAValidConfigurationFile() {
        Configuration.getInstance().setPersistentFile("src/test/resources/temperatures004.txt");
    }

    @Given("after launching the Server")
    public void givenAfterLaunchingTheServer() throws IOException {
        delay(100);
        startServer();
    }

    @When("the client ask the temperature for $city")
    @Alias("client ask the temperature for <city>")
    public void whenTheClientAskTheTemperatureForCity(@Named("city") String city) throws UnknownHostException, IOException, Exception {
        Client client = new Client();
        temperatures = client.getTemperature(city);
    }

    @Then("it returns no temperature")
    public void thenItReturnsNoTemperature() {
        assertArrayEquals(new Float[0], temperatures);
    }

    @Then("it returns $temperatures")
    public void thenItReturnsThetemperatures(@Named("temperatures") String expectedTemperatures) {
        assertEquals(expectedTemperatures, Arrays.toString(temperatures));
    }

    @BeforeScenario
    public void beforeScenario() {
        temperatures = new Float[0];
    }

    @AfterScenario
    public void afterScenario() throws IOException {
        //stopServer();
    }

    private void startServer() {
        if (server != null)
            return;
        server = new Server();
        server.start();
        while (!isServerRunning()) {
            System.out.println("starting");
            delay(100);
        }
    }

   
    private boolean isServerRunning() {
        try {
            Socket socket = new Socket("localhost", Configuration.getInstance().getPort());
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex1) {
            Logger.getLogger(RetrieveTemperatureSteps.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
}
