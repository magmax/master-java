/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import javabeans.MensajeForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Operaciones;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author miguel
 */
public class RegistrarAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "registrado";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        MensajeForm men = (MensajeForm) form;
        //obtiene los parámetros de contexto relativos
        //a la base de datos y crea el objeto del Modelo
        String driver = this.getServlet().getServletContext().
                getInitParameter("driver");
        String cadenacon = this.getServlet().getServletContext().
                getInitParameter("cadenacon");
        Operaciones oper = new Operaciones(driver, cadenacon);
        oper.registro(men);
        return mapping.findForward(SUCCESS);
    }
}
