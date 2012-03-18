<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>"Tienda Virtual"</title>
        <link rel="stylesheet" type="text/css" href="style/main.css" />
            
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
        <html:base/>
    </head>
    <body>

    <center>
        <h1>Autenticación de usuarios</h1>
        <br/>
        <html:form action="authenticate" method="post">
            <table class="ui-widget-content">
                <tr>
                    <td>Usuario:</td>
                    <td><html:text property="username" styleClass="ui-widget"/></td>
                    <td><html:errors property="auth.username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><html:password property="password" styleClass="ui-widget"/></td>
                    <td><html:errors property="auth.password"/></td>
                </tr>
            </table>
            <html:submit value="Entrar"/>
        </html:form>
    </center>
</body>
</html:html>
