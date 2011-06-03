<%-- 
    Document   : registromensajes
    Created on : 21-may-2011, 18:15:37
    Author     : miguel
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
    <head>
        <title>Página de envío de mensajes</title>
    </head>
    <body>
        <h1>Generación de mensajes</h1>
        <html:form action="registrar.do" method="post">
            <br/><br/>
            Introduzca destinatario:
            <html:text property="destino"/><br/><br/>
            Introduzca remitente:
            <html:text property="remite"/><br/><br/>
            Introduzca texto: <br/>
            <html:textarea property="texto" >
            </html:textarea>
            <br/>
            <center>
                <html:submit property="submit" value="Enviar"/>
            </center>
        </html:form>
    </body>
</html:html>

