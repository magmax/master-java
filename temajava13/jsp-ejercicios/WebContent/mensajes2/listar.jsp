<%@page import="mensajes2.Persistencia,mensajes2.Mensaje, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>Remite</th>
				<th>Texto</th>
			</tr>
		</thead>
		<tbody>
			<%
				String destino = request.getParameter("destino");
				Persistencia persistencia = Persistencia.getInstance();

				List<Mensaje> mensajes = persistencia.retrieve(destino);
				for (Mensaje msg : mensajes) {
			%>
				<tr>
					<td><%=msg.getRemite() %></td><td><%=msg.getTexto() %></td>
				</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>