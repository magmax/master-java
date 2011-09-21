/*
 * Copyright (C) 2011 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package practica4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class PersonaPersitence {
    /*  
     * En este caso se podría haber escrito el objeto directamente a disco, 
     * pero prefiero serializar mediante un archivo "properties".
     * Esto es algo más complejo, pero permite mayor versatilidad.
     * Precisamente por esta versatilidad y por probar cosas desconocidas,
     * utilizo Introspección para realizar el almacenamiento y recuperación.
     */

    private static final String FILENAME = "persona.dat";

    public static Persona load() throws Exception {
        Persona result = new Persona();
        Properties properties = new Properties();
        try {
            InputStream stream = new FileInputStream(FILENAME);
            properties.load(stream);
        } catch (FileNotFoundException e) {
            // Ignorar
            System.out.println("Fichero no encontrado. Se creará uno nuevo.");
        }
        for (Method each : Persona.class.getMethods()) {
            if (!isSetter(each)) {
                continue;
            }
            each.invoke(result, properties.getProperty(getPropertyForMethod(each), ""));
        }

        return result;
    }

    public static void save(Persona persona) throws Exception {
        Properties properties = new Properties();
        for (Method each : Persona.class.getMethods()) {
            if (!isGetter(each)) {
                continue;
            }
            properties.setProperty(getPropertyForMethod(each), each.invoke(persona).toString());
        }
        OutputStream stream = new FileOutputStream(FILENAME);
        properties.store(stream, "Objeto Persona para la práctica 4 del Máster en Java y J2EE.");
    }

    private static boolean isGetter(Method each) {
        if ("getClass".equals(each.getName())) {
            return false;
        }
        return each.getName().startsWith("get");
    }

    private static boolean isSetter(Method each) {
        if (!each.getName().startsWith("set")) {
            return false;
        }
        for (Class<?> c : each.getParameterTypes()) {
            if (c == String.class) {
                return true;
            }
        }
        return false;
    }

    private static String getPropertyForMethod(Method method) {
        return method.getName().substring(3);
    }
}
