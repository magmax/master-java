<%-- 
    Document   : busqueda
    Created on : 23-may-2011, 6:14:30
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Búsqueda de alumnos</title>
    </head>
    <body>
    <center>
        <html:form action="buscaralumno" method="post">
            DNI del alumno a buscar: <html:text property="dni"/><br/>
            <html:submit value="Buscar"/>
        </html:form>
    </center>
    </body>
</html:html>
