<%@page import="mensajes3.Persistencia,mensajes3.Mensaje,java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<jsp:useBean id="pers" class="mensajes3.Persistencia"/>
			<jsp:setProperty property="driver" name="pers" value="${initParam['dbdriver'] }"/>
			<jsp:setProperty property="connection" name="pers" value="${initParam['dbconnection'] }"/>
			<jsp:setProperty property="destino" name="pers" value="${ param.destino }"/>
				
			<c:forEach var="msg"	items="${ pers.messages }">
				<tr>
					<td><c:out value="${ msg.remite }"/></td>
					<td><c:out value="${ msg.texto }"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>