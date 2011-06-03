package servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
public class VerMensajesAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "mensajes";
    private final static String SINMENSAJES = "nomensajes";

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
        //obtiene los parámetros de contexto relativos
        //a la base de datos y crea el objeto del Modelo
        String driver = this.getServlet().getServletContext().
                getInitParameter("driver");
        String cadenacon = this.getServlet().getServletContext().
                getInitParameter("cadenacon");
        Operaciones oper = new Operaciones(driver, cadenacon);
        //recupera los mensajes asociados al destinatario
        ArrayList mensajes = oper.getMensajes(request.getParameter("nombre"));
        if (mensajes == null || mensajes.isEmpty()) {
            return mapping.findForward(SINMENSAJES);
        } else {
            request.setAttribute("mensajes", mensajes);
            return mapping.findForward(SUCCESS);
        }
    }
}
