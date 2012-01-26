<%@page import="org.magmax.master.practica8.Level"%>
<%@page import="java.util.Enumeration"%>
<%@page import="org.magmax.master.practica8.pojo.Question"%>
<%@page import="org.magmax.master.practica8.Domain"%>
<%@page import="java.util.List"%>
<%@page errorPage="index.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    Question[] last_exam = (Question[]) session.getAttribute("last_exam");

    if (last_exam == null) {
        System.out.println("ShowResult: no last exam.");
        response.sendRedirect("index.jsp");
    }

    Integer[] answers = (Integer[]) session.getAttribute("answers");
    if (answers == null) {
        answers = new Integer[last_exam.length];
    }

    for (int i = 0; i < last_exam.length; ++i) {
        String value = request.getParameter("answer" + i);
        answers[i] = value == null ? 0 : Integer.valueOf(value);
    }

    Domain domain = new Domain(application.getInitParameter("database"));
    Question[] solved = domain.solve(last_exam, answers);

    session.setAttribute("answers", answers);

    Integer ilevel = Integer.valueOf((String)session.getAttribute("level"));
    Level level = Level.resolve(ilevel);
    
    int punctuation = solved.length;
    String message = domain.getResultMessage(level, punctuation);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Master Java Practica 8: QuizMaster</title>
    </head>
    <body>
        <h1>Resultados</h1>

        <div class="examresult">
            <%=message%>

            <ul>
                <%
                    for (int i = 0; i < solved.length; ++i) {
                        Question question = solved[i];
                %>
                <div class="question">
                    <li><%=question.getDescription()%></li>
                    <ul>
                        <li>
                            <%=question.getAnswer(question.getCorrect())%>
                        </li>
                    </ol>
                </div>
                <%
                    }
                %>
            </ul>
        </div>
            
            <a href="index.jsp">Volver</a>
    </body>
</html>
