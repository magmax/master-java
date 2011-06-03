/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.MensajeForm;

/**
 *
 * @author miguel
 */
public class Operaciones {

    private String driver, cadenacon;

    public Operaciones(String driver, String cadenacon) {
        this.driver = driver;
        this.cadenacon = cadenacon;
    }

    public Connection obtenerConexion() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(cadenacon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }

    public ArrayList<MensajeForm> getMensajes(String destino) {
        Connection cn = null;
        ArrayList<MensajeForm> lista = null;
        Statement st;
        ResultSet rs;
        try_create();
        try {
            cn = obtenerConexion();
            st = cn.createStatement();
            String tsql;
            tsql = "select * from mensajes where destino='"
                    + destino + "'";
            rs = st.executeQuery(tsql);
            lista = new ArrayList<MensajeForm>();
            while (rs.next()) {
                MensajeForm m = new MensajeForm(rs.getString("remitente"),
                        rs.getString("destino"),
                        rs.getString("texto"));
                lista.add(m);
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (lista);
    }

    public void registro(MensajeForm m) {
        Connection cn;
        Statement st;
        ResultSet rs;
        try_create();
        try {
            cn = obtenerConexion();
            st = cn.createStatement();
            String tsql;
            tsql = "Insert into mensajes values('";
            tsql += m.getDestino() + "','"
                    + m.getRemite() + "','"
                    + m.getTexto() + "')";
            st.execute(tsql);
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void try_create ()
    {
        try {
            Connection con = obtenerConexion();
            Statement st = con.createStatement();
            st.execute("create table mensajes (remitente varchar(50), destino varchar(50), "
					+ "texto varchar(1024))");
            con.close();
        } catch (SQLException ex) {
        }
        
    }
}
