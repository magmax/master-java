<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.util.Calendar" %>
<%!static int visitas = 1;
	static Date last_date = null;%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Bienvenido.</p>

	<%
		if (visitas == 1) {
	%>
	<p>Ésta es su primera visita</p>
	<%
		} else {
	%>
	<p>Lleva <%=visitas %> visitas, y la última se realizó el <%=last_date %>.
	<%
		}
	%>
	<% visitas++; last_date = Calendar.getInstance().getTime();%>
</body>
</html>