package comonentes;

import javax.ejb.Stateless;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
@Stateless
public class Saludo implements SaludoLocal {

    @Override
    public String obtenerMensaje(String name) {
        return "Bienvenido a mi EJB, " + name + "!!";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
