<%-- 
    Document   : error
    Created on : 20-jun-2011, 7:42:54
    Author     : Miguel Angel Garcia <miguelangel.garcia@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de error</title>
    </head>
    <body>
        <h1><%=request.getAttribute("mensaje")%></h1>
    </body>
</html>
