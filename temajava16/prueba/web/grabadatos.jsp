<%-- 
    Document   : grabardatos
    Created on : 26-may-2011, 20:56:27
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
        <center><h1>Formulario de datos</h1></center>

        <h:form>
            <table width="50%" align="center" border="0">
                <tr>
                    <td>Nombre:</td>
                    <td><h:inputText value="#{PersonaBean.nombre}"/></td>
                </tr>
                <tr>
                    <td>Teléfono:</td>
                    <td><h:inputText value="#{PersonaBean.telefono}"/></td>
                </tr>
                <tr>
                    <td>Edad:</td>
                    <td><h:inputText value="#{PersonaBean.edad}"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <h:commandButton value="Guardar" action="#{PersonaBean.doGuardar}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <h:commandButton value="Ver Todos" action="ver"/>
                    </td>
                </tr>
            </table>
        </h:form>

    </body>
</html>
</f:view>