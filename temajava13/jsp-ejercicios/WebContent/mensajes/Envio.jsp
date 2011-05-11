<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Envío</title>
</head>
<body>

	<%
		if (request.getParameter("texto") != null) {
	%>
	<jsp:useBean id="mensa" scope="request" class="mensajes.Mensaje" />
	<jsp:setProperty name="mensa" property="*" />
	<%--pasa la petición al servlet guardar
        para que grabe el mensaje en la BD--%>
	<jsp:forward page="guardar" />
	<%
		}
	%>
	<h1>Generación de mensajes</h1>
	<form method="post">
		<br /> <br /> Introduzca destinatario: <input type="text"
			name="destino" /><br /> <br /> Introduzca remitente: <input
			type="text" name="remite" /><br /> <br /> Introduzca texto: <br />
		<textarea name="texto">
        </textarea>
		<br />
		<center>
			<input type="submit" name="Submit" value="Enviar" />
		</center>
	</form>

</body>

</html>