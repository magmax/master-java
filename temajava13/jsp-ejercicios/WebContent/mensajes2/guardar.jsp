<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	if (request.getParameter("texto") != null) {
%>
<jsp:useBean id="msg" class="mensajes2.Mensaje" scope="request">
</jsp:useBean>
<jsp:setProperty property="*" name="msg" />
<jsp:forward page="Mensajes2Guardar"></jsp:forward>
<%
	} else {
%>
<jsp:forward page="enviar"></jsp:forward>
<%
	}
%>
