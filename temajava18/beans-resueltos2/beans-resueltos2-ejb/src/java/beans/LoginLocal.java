/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Local;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
@Local
public interface LoginLocal {

    boolean validar(String login, String password);
    
}
