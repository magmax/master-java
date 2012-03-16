<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>"Tienda Virtual"</title>
        <html:base/>
    </head>
    <body>

    <center>
        <h1>Autenticación de usuarios</h1>
        <br/>
        <html:form action="authenticate" method="post">
            <table>
                <tr>
                    <td>Usuario:</td>
                    <td><html:text property="username"/></td>
                    <td><html:errors property="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><html:password property="password"/></td>
                    <td><html:errors property="password"/></td>
                </tr>
                <table>
                    <html:submit value="Entrar"/>
                </html:form>
                </center>
                </body>
            </html:html>
