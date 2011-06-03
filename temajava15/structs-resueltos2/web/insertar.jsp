<%-- 
    Document   : insertar
    Created on : 23-may-2011, 6:22:29
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de inserción de alumnos</title>
    </head>
    <body>
    <center>
        <h1>Datos del alumno</h1>

        <html:form action="insertaralumno" method="post">

            <p>DNI: <html:text property="dni"/><html:errors property="dni"/></p> 
            <p>Nombre: <html:text property="nombre"/><html:errors property="nombre"/></p>
            <p>Edad: <html:text property="edad"/></p>
            <p>Email: <html:text property="email"/><html:errors property="email"/></p>
            <p>Curso: <html:text property="curso"/></p>
            <p>Fecha Alta: <html:text property="fecha_alta"/> (año/mes/día)</p>
            <html:submit value="Enviar"/>
        </html:form>
    </center>
</body>
</html:html>
