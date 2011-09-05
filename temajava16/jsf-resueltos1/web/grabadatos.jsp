<%-- 
    Document   : grabardatos
    Created on : 26-may-2011, 20:56:27
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center><h1>Formulario de datos</h1></center>
    <f:view>
        <h:form>
            <table width="50%" align="center" border="0">
                <tr>
                    <td>Nombre:</td>
                    <td>
                        <h:inputText id="nombre" value="#{PersonaBean.nombre}" required="true"/>
                        <h:message for="nombre"/>
                    </td>
                </tr>
                <tr>
                    <td>Teléfono:</td>
                    <td>
                        <h:inputText id="tel" value="#{PersonaBean.telefono}" required="true"/>
                        <h:message for="tel"/>
                    </td>
                </tr>
                <tr>
                    <td>Edad:</td>
                    <td>
                        <h:inputText id="edad" value="#{PersonaBean.edad}" required="true"/>
                        <h:message for="edad"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <h:commandButton value="Guardar" action="#{PersonaBean.doGuardar}" actionListener="#{PersonaBean.doHabilitar}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <h:commandButton value="Ver Todos" action="ver" rendered="false" id="vertodos"/>
                    </td>
                </tr>
            </table>
        </h:form>
    </f:view>
</body>
</html>
