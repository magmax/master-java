<%-- 
    Document   : listamensajes
    Created on : 21-may-2011, 18:22:12
    Author     : miguel
--%>
<%@ page import="javabeans.*,java.util.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Listado de Mensajes</title>
    </head>
    <body>
    <center>
        <h1>
            <bean:parameter id="usuario" name="nombre"/>
            Mensajes para <bean:write name="usuario"/>
        </h1>
        <table border=1>
            <tr><th>Remitente</th><th>Mensaje</th></tr>
            <logic:iterate name="mensajes" scope="request" id="mensa" type="MensajeForm">
                <tr>
                    <td><bean:write name="mensa" property="remite"/></td>
                    <td><bean:write name="mensa" property="texto"/></td>
                </tr>

            </logic:iterate>
        </table>
        <br/><br/>
        <a href="inicio.jsp">Inicio</a>
    </center>
</body>
</html>
