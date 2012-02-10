<%-- 
    Document   : newexam.jsp
    Created on : 07-feb-2012, 19:43:56
    Author     : miguel
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="org.magmax.master.practica8b.pojo.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Master Java Practica 8: QuizMaster</title>
    </head>
    <body>
        <h1>QuizMaster 2.0</h1>
        
        <%
        Enumeration<String> attributes = request.getAttributeNames(); 
        while (attributes.hasMoreElements()) {
            %>
            <br/><%=attributes.nextElement()%>
            <%  
        }
        %>
        Elementos:<%=request.getAttributeNames()%>
        <ol>
        <%
            Question[] exam = (Question[]) request.getAttribute("exam");
            //for (Question each : exam ) {
        %>
        <li>
            <%=exam%>
        </li>
        <%
               //}
        %>
        </ol>
    </body>
</html>
