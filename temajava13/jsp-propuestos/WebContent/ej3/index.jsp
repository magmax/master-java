<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="generado.jsp" method="post">
			<!-- encabezado -->
			<b>Introduce encabezado:</b><input type="text" name="encabezado" />
			<br />
			<!-- nivel -->
			<b>Elija el nivel del encabezado:</b><select name="nivel">
				<% for (int	i=1; i<7; ++i) { %>
				<option value="<%=i%>" <%if (i==1) {%>selected="true"<%}%>><%=i%></option>
				<%}%>
			</select> <br />
			<!-- Texto -->
			<b>Introduce texto:</b><br />
			<textarea rows="5" cols="40" name="texto"></textarea>
			<br />
			<!-- Estilos -->
			<b>Estilos del texto:</b><br/>
			<input type="checkbox" name="estilo" value="b">negrita</input><br/>
			<input type="checkbox" name="estilo" value="i">cursiva</input><br/>
			<!-- Color -->
			<b>Color</b><br/>
			<input type="radio" name="color" value="#000" checked="checked">Negro</input><br/>
			<input type="radio" name="color" value="#0f0">Verde</input><br/>
			<input type="radio" name="color" value="#00f">Azúl</input><br/>
			<br/>
			<!-- Submit -->
			<input type="submit" value="generar" />
		</form>
	</center>
</body>
</html>