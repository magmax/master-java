<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h${param.nivel}>${param.encabezado}</h${param.nivel}>
	<% for (String v : request.getParameterValues("estilo")) { %>
		<<%=v%>>
	<% } %>
	<p style="color:${param.color}">${param.texto }</p>
	<% for (String v : request.getParameterValues("estilo")) { %>
		</<%=v%>>
	<% } %>
</body>
</html>