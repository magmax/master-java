package javabeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class ConfiguracionBean {
    private String preciomemoria;
    private String[] precioaccesorios;
    private String envio;
    private double preciofinal = 300;
    private String datosconfiguracion;
    int pago;
    
    /** Creates a new instance of ConfiguracionBean */
    public ConfiguracionBean() {
    }

    public String getDatosconfiguracion() {
        return datosconfiguracion;
    }

    public void setDatosconfiguracion(String datosconfiguracion) {
        this.datosconfiguracion = datosconfiguracion;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int formaPago) {
        this.pago = formaPago;
    }

    public String[] getPrecioaccesorios() {
        return precioaccesorios;
    }

    public void setPrecioaccesorios(String[] precioaccesorios) {
        this.precioaccesorios = precioaccesorios;
    }

    public double getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }

    public String getPreciomemoria() {
        return preciomemoria;
    }

    public void setPreciomemoria(String preciomemoria) {
        this.preciomemoria = preciomemoria;
    }

    public String calcular () {
        double precio = getPreciofinal();
        
        precio += Double.parseDouble(getPreciomemoria());
        
        for ( String item : getPrecioaccesorios())
            precio += Double.parseDouble(item);
        
        if (getPago() == 1)
            precio -= precio * 0.1;
        
        setPreciofinal(precio);
        
        return "resultado";
    }
    
}
