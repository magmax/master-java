<%-- 
    Document   : ver
    Created on : 26-may-2011, 20:58:51
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="h"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center><h1>Listado de personas</h1></center>
    <table border="1" align="center">
        <tr>
            <th>Nombre</th>
            <th>Teléfono</th>
            <th>Edad</th>
        </tr>
        <c:forEach var="per" items="${listapersonas}">
            <tr>
                <td><c:out value="${per.nombre}"/></td>
                <td><c:out value="${per.telefono}"/></td>
                <td><c:out value="${per.edad}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
