<%-- 
    Document   : index
    Created on : 18-ene-2012, 9:17:48
    Author     : miguel
--%>

<%@page import="org.magmax.master.practica8.Domain"%>
<%@page import="org.magmax.master.practica8.Issue"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Master Java Practica 8: QuizMaster</title>
    </head>
    <body>
        <h1>QuizMaster 1.0</h1>

        <h2>Selector de exámenes</h2>

        <form action="NewExam" method="POST">
            <div class="issue">
                <p>Tema:
                    <select name="issue">
                        <% 
                        String selected="selected=\"true\"";
                        Domain domain = new Domain(application.getInitParameter("database"));
                        for (Issue each : domain.getIssues()) 
                        { 
                        %>
                            <option label="<%=each.getName()%>" value="<%=each.getId()%>" <%=selected%>/>
                        <% 
                            selected = "";
                        } 
                        %>
                    </select>
                </p>
            </div>
            <div class="difficulty">
                <p>Dificultad:
                <ul>
                    <li><input type="radio" name="difficulty" value="low" checked="true">Baja</input></li>
                    <li><input type="radio" name="difficulty" value="medium">Media</input></li>
                    <li><input type="radio" name="difficulty" value="high">Alta</input></li>
                </ul>
                </p>
            </div>
            <input type="submit" value="Generar Examen"/>
        </form>
    </body>
</html>
