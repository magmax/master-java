package javabeans;

import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miguel
 */
@ManagedBean
public class PersonaBean {

    private String nombre;
    private long telefono;
    private int edad;

    /** Creates a new instance of PersonaBean */
    public PersonaBean() {
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String doGuardar() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        ArrayList<PersonaBean> listapersonas;
        listapersonas = (ArrayList<PersonaBean>) sesion.getAttribute("listapersonas");
        //comprueba si ya existe la colección de personas
//en la sesión y si no es así la crea
        if (listapersonas == null) {
            listapersonas = new ArrayList<PersonaBean>();
            sesion.setAttribute("listapersonas", listapersonas);
        }
        listapersonas.add(this);
        return null;
    }
}
