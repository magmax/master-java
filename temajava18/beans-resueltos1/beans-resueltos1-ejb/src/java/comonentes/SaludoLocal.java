/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comonentes;

import javax.ejb.Local;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
@Local
public interface SaludoLocal {

    String obtenerMensaje(String name);
    
}
