<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="guardar.jsp" method="post">
		<input type="text" name="remite"/><br/>
		<input type="text" name="destino"/><br/>
		<textarea name="texto" rows="5" cols="100"></textarea><br/>
		<input type="submit" value="Enviar"/>
	</form>
</body>
</html>