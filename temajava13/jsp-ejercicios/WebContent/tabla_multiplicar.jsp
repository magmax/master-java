<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tabla de multiplicar</title>
</head>
<body>
	<table>
		<% for(int i=1; i<11; ++i) { %>
		<tr>
			<% for (int j=1; j<11; ++j) { %>
			<td><%=i*j%></td>
			<% } %>
		</tr>
		<% } %>
	</table>
</body>
</html>