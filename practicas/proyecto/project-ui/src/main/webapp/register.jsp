<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>"Tienda Virtual"</title>
        <html:base/>
    </head>
    <body>

    <center>
        <h1>Registro de usuarios</h1>
        <br/>
        <p>El usuario no se encontró. Tal vez quiera usted registrarse.</p>

        <html:form action="register" method="post">
            <table>
                <tr>
                    <td>Usuario:</td>
                    <td><html:text property="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><html:text property="password"/></td>
                </tr>

                <tr>
                    <td>E-mail:</td>
                    <td><html:text property="email"/></td>
                </tr>

                <tr>
                    <td>Dirección:</td>
                    <td><html:text property="address"/></td>
                </tr>


                <tr>
                    <td>Teléfono:</td>
                    <td><html:text property="phone"/></td>
                </tr>
                <table>
                    <html:submit value="Registrarse"/>
                </html:form>
                    <br/>
                    <br/>
                    </center>
                <html:link forward="welcome">Volver</html:link>
                </body>
            </html:html>
