package javabeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class PreciosBean {

    private List<SelectItem> memoria;
    private List<SelectItem> accesorios;

    /** Creates a new instance of PreciosBean */
    public PreciosBean() {
    }

    public Connection obtenerConexion() {
        Connection cn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            cn = DriverManager.getConnection("jdbc:hsqldb:mem:myDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }
    
    private void try_create_memoria ()
    {
        try {
            Connection con = obtenerConexion();
            Statement st = con.createStatement();
            st.execute("create table memoria ("
                    + "capacidad varchar(50), "
                    + "precio varchar(50)"                    
                    + ")");
            st.execute("insert into memoria(capacidad,precio) values('2Gb', '200')");
            st.execute("insert into memoria(capacidad,precio) values('4Gb', '300')");
            st.execute("insert into memoria(capacidad,precio) values('8Gb', '400')");
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    private void try_create_accesorios ()
    {
        try {
            Connection con = obtenerConexion();
            Statement st = con.createStatement();
            st.execute("create table accesorios ("
                    + "nombre varchar(50), "
                    + "precio varchar(50)"
                    + ")");
            st.execute("insert into accesorios(nombre,precio) values('Monitor TFT', '300')");
            st.execute("insert into accesorios(nombre,precio) values('Lector Blue Ray', '300')");
            st.execute("insert into accesorios(nombre,precio) values('Impresora Laser Color', '300')");
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    public List<SelectItem> getMemoria() {
        //obtiene la lista de precios de los
        //módulos de memoria
        try_create_memoria();
        String sql = "";
        ArrayList<SelectItem> totales =
                new ArrayList<SelectItem>();
        try {
            Connection cn = obtenerConexion();
            Statement st = cn.createStatement();
            sql = "SELECT capacidad, precio FROM memoria";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                totales.add(new SelectItem(
                        rs.getString("precio"), rs.getString("capacidad")));
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            memoria = totales;
            return memoria;
        }
    }

    public List<SelectItem> getAccesorios() {
        //obtiene la lista de precios de los
        //accesorios
        try_create_accesorios();
        String sql = "";
        ArrayList<SelectItem> totales =
                new ArrayList<SelectItem>();
        try {
            Connection cn = obtenerConexion();
            Statement st = cn.createStatement();
            sql = "SELECT nombre, precio FROM accesorios";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                totales.add(new SelectItem(
                        rs.getString("precio"), rs.getString("nombre")));
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            accesorios = totales;
            return accesorios;
        }
    }

    public void setMemoria(List<SelectItem> memoria) {
        this.memoria = memoria;
    }

    public void setAccesorios(List<SelectItem> accesorios) {
        this.accesorios = accesorios;
    }
}
