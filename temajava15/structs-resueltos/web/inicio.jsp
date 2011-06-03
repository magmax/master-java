<%-- 
    Document   : inicio
    Created on : 21-may-2011, 18:17:57
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!DOCTYPE html>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <center>
        <table>
            <tr>
            <td><html:link forward="paginaregistro">
                    <bean:message key="inicio.enviar"/></html:link>
            </td>
            </tr>
            <tr>
            <td><html:link forward="paginasolicitud">
                  <bean:message key="inicio.ver"/></html:link>
            </td>
            </tr>
        </table>
    </center>
  </body>
</html>
