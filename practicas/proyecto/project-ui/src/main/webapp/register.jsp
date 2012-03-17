<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>"Tienda Virtual"</title>
        <link rel="stylesheet" type="text/css" href="style/main.css" />
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
                    <td class="error"><html:errors property="register.username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><html:password property="password"/></td>
                    <td class="error"><html:errors property="register.password"/></td>
                </tr>

                <tr>
                    <td>E-mail:</td>
                    <td><html:text property="email"/></td>
                    <td class="error"><html:errors property="register.email"/></td>
                </tr>

                <tr>
                    <td>Dirección:</td>
                    <td><html:text property="address"/></td>
                    <td class="error"><html:errors property="register.address"/></td>
                </tr>

                <tr>
                    <td>Teléfono:</td>
                    <td><html:text property="phone"/></td>
                    <td class="error"><html:errors property="register.phone"/></td>
                </tr>
            </table>
            <html:submit value="Registrarse"/>
        </html:form>
        <br/>
        <br/>
    </center>
    <html:link forward="welcome">Volver</html:link>
    </body>
</html:html>
