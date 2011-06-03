/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javabeans.AlumnoForm;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 *
 * @author miguel
 */
public class Operations {
    private String driver;
    private String cadenacon;

    public Operations(String driver, String cadenacon) {
        this.driver = driver;
        this.cadenacon = cadenacon;
    }
    
    private Connection obtenerConexion() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(cadenacon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }
    
    private void try_create ()
    {
        try {
            Connection con = obtenerConexion();
            Statement st = con.createStatement();
            st.execute("create table alumnos ("
                    + "dni varchar(12), "
                    + "nombre varchar(50), "
                    + "edad integer,"
                    + "email varchar(50),"
                    + "curso varchar(100),"
                    + "fecha_alta datetime"
                    + ")");
            con.close();
        } catch (SQLException ex) {
        }
        
    }

    public void insertar(AlumnoForm alumno) throws SQLException, ParseException {
        try_create();
        Connection cn = obtenerConexion();
        PreparedStatement st = cn.prepareStatement("insert into alumnos (dni,nombre,edad,email,curso,fecha_alta) values (?,?,?,?,?,?)");
        st.setString(1, alumno.getDni());
        st.setString(2, alumno.getNombre());
        st.setInt(3, alumno.getEdad());
        st.setString(4, alumno.getEmail());
        st.setString(5, alumno.getCurso());
        st.setDate(6, new Date(alumno.getFecha_altaAsDate().getTime()));
        
        st.execute();
        
        cn.close();
    }

    public List<AlumnoForm> getAlumnos() throws SQLException {
        try_create();
        Connection cn = obtenerConexion();
        Statement st = cn.createStatement();
        ResultSet resultset = st.executeQuery("select dni,nombre,edad,email,curso,fecha_alta from alumnos");
        
        List<AlumnoForm> result = new ArrayList<AlumnoForm>();
        
        while (resultset.next())
        {
            AlumnoForm item = new AlumnoForm();
            item.setDni(resultset.getString("dni"));
            item.setNombre(resultset.getString("nombre"));
            item.setEdad(resultset.getInt("edad"));
            item.setEmail(resultset.getString("email"));
            item.setCurso(resultset.getString("curso"));
            item.setFecha_alta(resultset.getString("fecha_alta"));

            result.add(item);   
        }
        
        cn.close();
        
        return result;
    }

    public AlumnoForm getAlumno(String dni) throws SQLException {
        try_create();
        Connection cn = obtenerConexion();
        PreparedStatement st = cn.prepareStatement("select dni,nombre,edad,email,curso,fecha_alta from alumnos where dni=?");
        st.setString(1, dni);
        ResultSet resultset = st.executeQuery();
        
        AlumnoForm result = null;
        
        if (resultset.next())
        {
            result = new AlumnoForm();
            result.setDni(resultset.getString("dni"));
            result.setNombre(resultset.getString("nombre"));
            result.setEdad(resultset.getInt("edad"));
            result.setEmail(resultset.getString("email"));
            result.setCurso(resultset.getString("curso"));
            result.setFecha_alta(resultset.getString("fecha_alta"));
        }
        
        cn.close();
        
        return result;
    }
}
