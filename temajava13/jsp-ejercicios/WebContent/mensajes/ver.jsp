<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="mensajes.Mensaje,java.util.*,mensajes.Operaciones"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String nombre = request.getParameter("nombre");
		String driver = application.getInitParameter("driver");
		String cadenacon = application.getInitParameter("cadenacon");
		Operaciones op = new Operaciones(driver, cadenacon);
	%>
	<h1>
		Mensajes para
		<%=nombre%></h1>
	<table border="1">
		<tr>
			<th>Remitente</th>
			<th>Mensaje</th>
		</tr>
		<%
			ArrayList<Mensaje> lista = op.getMensajes(nombre);
			if (lista != null && lista.size() > 0) {
				for (Mensaje m : lista) {
		%>
		<tr>
			<td><%=m.getRemite()%></td>
			<td><%=m.getTexto()%></td>
		</tr>
		<%
			}
			} else {
		%>
		<jsp:forward page="nomensajes.jsp" />
		<%
			}
		%>
	</table>
	<br />
	<a href="inicio.html">Inicio</a>

</body>
</html>