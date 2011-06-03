/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


/**
 *
 * @author miguel
 */
public class AlumnoForm extends org.apache.struts.action.ActionForm {

    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String dni;
    private String nombre;
    private int edad;
    private String email;
    private String curso;
    private String fecha_alta = dateformat.format(Calendar.getInstance().getTime());

    public AlumnoForm() {
        super();
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public java.util.Date getFecha_altaAsDate() throws ParseException {
        return dateformat.parse(fecha_alta);
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors result = new ActionErrors();
         
        if (isDniErroneous())
            result.add("dni", new ActionMessage("alumno.error.dni"));
        if (isNombreErroneous())
            result.add("nombre", new ActionMessage("alumno.error.nombre"));
        if (isMailErroneous())
            result.add("email", new ActionMessage("alumno.error.mail"));
        
        return result;
    }

    private boolean isMailErroneous() {
        return ! email.matches(".+@.+\\..+");
    }

    private boolean isDniErroneous() {
        return dni == null || dni.isEmpty();
    }

    private boolean isNombreErroneous() {
        return nombre == null || nombre.isEmpty();
    }
    
    
}
