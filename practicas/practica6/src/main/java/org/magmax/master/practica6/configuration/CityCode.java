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
package org.magmax.master.practica6.configuration;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public enum CityCode {

    ALAVA(1, "Álava"),
    ALBACETE(2, "Albacete"),
    ALICANTE(3, "Alicante"),
    ALMERIA(4, "Almería"),
    ASTURIAS(5, "Asturias"),
    AVILA(6, "Ávila"),
    BADAJOZ(7, "Badajoz"),
    BALEARES(8, "Islas Baleares"),
    BARCELONA(9, "Barcelona"),
    BURGOS(10, "Burgos"),
    CACERES(11, "Cáceres"),
    CADIZ(12, "Cádiz"),
    CANTABRIA(13, "Cantabria"),
    CASTELLON(14, "Castellón"),
    CEUTA(15, "Céuta"),
    CIUDAD_REAL(16, "Ciudad Real"),
    CORDOBA(17, "Córdoba"),
    CUENCA(18, "Cuenca"),
    GERONA(19, "Gerona"),
    GRANADA(20, "Granada"),
    GUADALAJARA(21, "Guadalajara"),
    GUIPUZCOA(22, "Guipuzcoa"),
    HUELVA(23, "Huelva"),
    HUESCA(24, "Huesca"),
    JAEN(25, "Jaén"),
    LAS_PALMAS(26, "Las Palmas de Gran Canaria"),
    LA_CORUÑA(27, "La Coruña"),
    LA_RIOJA(28, "La Rioja"),
    LEON(29, "León"),
    LERIDA(30, "Lérida"),
    LUGO(31, "Lugo"),
    MALAGA(32, "Málaga"),
    MELILLA(33, "Melilla"),
    MURCIA(34, "Murcia"),
    NAVARRA(35, "Navarra"),
    ORENSE(36, "Orense"),
    PALENCIA(37, "Palencia"),
    PONTEVEDRA(38, "Pontevedra"),
    SALAMANCA(39, "Salamanca"),
    SANTA_CRUZ_DE_TENERIFE(40, "Santa Cruz de Tenerife"),
    SEGOVIA(41, "Segovia"),
    SEVILLA(42, "Sevilla"),
    SORIA(43, "Soria"),
    TARRAGONA(44, "Tarragona"),
    TERUEL(45, "Teruel"),
    TOLEDO(46, "Toledo"),
    VALENCIA(47, "Valencia"),
    VALLADOLID(48, "Valladolid"),
    VIZCAYA(49, "Vizcaya"),
    ZAMORA(50, "Zamora"),
    ZARAGOZA(51, "Zaragoza");
    public final int code;
    public final String description;

    private CityCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CityCode getFromCode(int code) throws Exception {
        for (CityCode each : values()) {
            if (each.code == code) {
                return each;
            }
        }
        throw new Exception("Provincia no encontrada");
    }
}
