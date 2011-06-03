<%-- 
    Document   : operaciones
    Created on : 23-may-2011, 5:55:17
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Operaciones</title>
    </head>
    <body>
    <center>
        <p><html:link forward="busqueda"><bean:message key="operaciones.buscar"/></html:link></p>
        <p><html:link action="listadoalumnos"><bean:message key="operaciones.listado"/></html:link></p>
        <p><html:link forward="insertar"><bean:message key="operaciones.insertar"/></html:link></p>
    </center>
    </body>
</html>
