<%-- 
    Document   : index
    Created on : 18-ene-2012, 9:17:48
    Author     : miguel
--%>

<%@page import="org.magmax.master.practica8b.pojo.Issue"%>
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
        <h1>QuizMaster 2.0</h1>

        <h2>Selector de exámenes</h2>

        <form action="" method="POST">
            <div class="issue">
                <p>Tema:
                    <select name="issue">
                        <% 
                        String selected="selected=\"true\"";
                        for (Issue each : (Issue[]) request.getAttribute("issue_list")) 
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
                    <li><input type="radio" name="level" value="1" checked="true">Baja</input></li>
                    <li><input type="radio" name="level" value="2">Media</input></li>
                    <li><input type="radio" name="level" value="3">Alta</input></li>
                </ul>
                </p>
            </div>
            <input type="submit" value="Generar Examen"/>
        </form>
    </body>
</html>
