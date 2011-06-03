<%-- 
    Document   : sinmensajes
    Created on : 21-may-2011, 18:23:19
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
    <head>
        <title>Página de aviso</title>
    </head>
    <body>
        <center>
           <h2>El destinatario:
               <bean:parameter id="usuario" name="nombre"/>
               <bean:write name="usuario"/>
                  no tiene mensajes
            </h2>
        </center>
    </body>
</html>
