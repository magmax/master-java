<%-- 
    Document   : mostraralumno
    Created on : 23-may-2011, 20:59:29
    Author     : miguel
--%>
<%@page import="javabeans.AlumnoForm" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de datos</title>
    </head>
    <body>
        <h1>Datos del alumno solicitado</h1>
        <p>Nombre:<bean:write name="alumno" property="nombre"/></p>
        <p>Edad:<bean:write name="alumno" property="edad"/></p>
        <p>Email:<bean:write name="alumno" property="email"/></p>
        <p>Curso matriculado:<bean:write name="alumno" property="curso"/></p>
    </body>
</html>
