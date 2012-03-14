<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="/META-INF.tld/struts-html.tld" prefix="html" %>
<!--
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
-->
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="Tienda Virtual"/></title>
        <html:base/>
    </head>
    <body>
                
        <h1>Autenticación de usuarios</h1>
        <br/>
    <center>
    <html:form action="authenticate" method="post">
        <table>
            <tr>
                <td>Usuario:</td>
                <td><html:text property="name"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><html:password property="password"/></td>
            </tr>
        <table>
    </html:form>
    </center>
    </body>
</html:html>
