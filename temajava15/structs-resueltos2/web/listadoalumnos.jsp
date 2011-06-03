<%-- 
    Document   : listadoalumnos
    Created on : 23-may-2011, 7:52:29
    Author     : miguel
--%>
<%@page import="javabeans.*,java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de alumnos</title>
    </head>
    <body>
        <h1>Listado de alumnos matriculados</h1>
        
        <table style="border:1px">
            <thead>
                <tr>
                    <td>Nombre Alumno</td>
                    <td>Edad</td>
                    <td>Email</td>
                    <td>Curso</td>
                </tr>
            </thead>
            <logic:iterate id="item" type="AlumnoForm" name="alumnos">
                <tr>
                    <td><bean:write name="item" property="nombre"/></td>
                    <td><bean:write name="item" property="edad"/></td>
                    <td><bean:write name="item" property="email"/></td>
                    <td><bean:write name="item" property="curso"/></td>
                </tr>
            </logic:iterate>
        </table>
    </body>
</html>
