<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="ej5.Persistence,ej5.Tema,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Añadir libro</title>
</head>
<body>

	<center>

		<form method="post" action="Savebook">
			<!-- Título -->
			<p>
				Título: <input type="text" name="titulo" />
			</p>
			<!-- Autor -->
			<p>
				Autor: <input type="text" name="autor" />
			</p>
			<!-- Precio -->
			<p>
				Precio: <input type="text" name="precio" />
			</p>
			<!-- Tema -->
			<p>
				<jsp:useBean id="pers" class="ej5.Persistence"/>
				<select name="idtema">
					<c:forEach var="tema" items="${pers.temas}">
						<option value="${ tema.id }"> ${ tema.tema } </option>
					</c:forEach>
				</select>
			</p>

			
			<input type="submit" value="Guardar Libro" />

		</form>

	</center>

</body>
</html>