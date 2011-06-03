<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="ej5.Persistence,ej5.Tema,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<table>
			<thead>
				<tr>
					<th>Título</th>
					<th>Autor</th>
					<th>Precio</th>
					<th>Tema</th>
				</tr>
			</thead>
			<tbody>
				<jsp:useBean id="pers" class="ej5.Persistence" />
				<c:forEach var="libro" items="${pers.libros}">
					<tr>
						<td> <c:out value="${libro.titulo}"/> </td>
						<td> <c:out value="${libro.autor}"/> </td>
						<td> <c:out value="${libro.precio}"/> </td>
						<td> <c:out value="${libro.tema}"/> </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</center>
</body>
</html>