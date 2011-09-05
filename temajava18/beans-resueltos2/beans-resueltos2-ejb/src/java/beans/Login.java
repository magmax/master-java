package beans;

import javax.ejb.Stateless;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
@Stateless
public class Login implements LoginLocal {

    public boolean validar(String login, String password) {
        return "admin".equals(login) && "admin".equals(password);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
