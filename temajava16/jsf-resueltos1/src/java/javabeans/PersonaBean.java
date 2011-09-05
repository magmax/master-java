/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miguel
 */
public class PersonaBean {

    private String nombre;
    private long telefono;
    private int edad;

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
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        ArrayList<PersonaBean> listapersonas;
        listapersonas = (ArrayList<PersonaBean>) session.getAttribute("listapersonas");
        if (listapersonas == null) {
            listapersonas = new ArrayList<PersonaBean>();
            session.setAttribute("listapersonas", listapersonas);
        }
        listapersonas.add(this);
        return null;
    }

    public void doHabilitar(ActionEvent ev) {
        UIComponent cp = ev.getComponent();
        UIComponent btnver = cp.findComponent("vertodos");
        btnver.setRendered(true);
    }
}
