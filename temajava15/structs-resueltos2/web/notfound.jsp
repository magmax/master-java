<%-- 
    Document   : notfound
    Created on : 24-may-2011, 5:42:48
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User not found</title>
    </head>
    <body>
        <h1>User not found</h1>
        <p>El usuario con el DNI '<bean:write name="alumno" property="dni"/>' no se encontró.</p>
    </body>
</html>
