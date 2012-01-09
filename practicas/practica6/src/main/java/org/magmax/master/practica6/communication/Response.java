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
package org.magmax.master.practica6.communication;

import java.util.ArrayList;
import org.magmax.master.practica6.configuration.CityCode;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class Response {
    
    private CityCode citycode;
    private ArrayList<Float> temperatures = new ArrayList<Float>();
    
    public CityCode getCitycode() {
        return citycode;
    }
    
    public void setCitycode(CityCode citycode) {
        this.citycode = citycode;
    }
    
    public void addTemperature(float temperature) {
        temperatures.add(Float.valueOf(temperature));
    }
    
    public Float[] getTemperatures() {
        return temperatures.toArray(new Float[0]);
    }
}