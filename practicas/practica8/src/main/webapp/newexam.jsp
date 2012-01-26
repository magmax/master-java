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
    if (request.getParameter("issue") == null || request.getParameter("difficulty") == null) {
        System.out.println("New Exam: no issue or difficulty");
        session.setAttribute("issue", null);
        session.setAttribute("level", null);
        response.sendRedirect("index.jsp");
    }
    
    if (request.getParameter("issue").toString().equals(session.getAttribute("issue")) 
            && request.getParameter("difficulty").toString().equals(session.getAttribute("level"))) {
        System.out.println("New Exam: issue and difficulty as stored");
        response.sendRedirect("showresult.jsp");
    }

    Integer issue = Integer.valueOf (request.getParameter("issue"));
    Integer level = Integer.valueOf (request.getParameter("difficulty"));
    
    Question[] last_exam;
    Domain domain = new Domain(application.getInitParameter("database"));
    last_exam = domain.generateExam(level, issue);
    
    session.setAttribute("issue", issue.toString());
    session.setAttribute("level", level.toString());
    session.setAttribute("last_exam", last_exam);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Master Java Practica 8: QuizMaster</title>
    </head>
    <body>
        <h1>Examen</h1>
        
        <form action="showresult.jsp" method="POST">
            <div class="exam">
                <ol>
                <%
                    for (int i = 0; i < last_exam.length; ++i) 
                    {
                        Question question = last_exam[i];
                %>
                <div class="question">
                    <li><%=question.getDescription()%></li>
                    <ol>
                        <%for (int j = 1; j < 5; ++j) {%>
                            <li>
                                <input type="radio" name="answer<%=i%>" value="<%=j%>">
                                    <%=question.getAnswer(j)%>
                                </input>
                            </li>
                        <%}%>
                    </ol>
                </div>
                <%
                    }
                %>
                </ol>
                
                <input type="submit" value="Submit"/>
                
            </div>
        </form>
    </body>
</html>
