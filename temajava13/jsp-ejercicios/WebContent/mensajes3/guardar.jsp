<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${ not empty param.texto }">
	<jsp:useBean id="msg" class="mensajes3.Mensaje" scope="request" />
	<jsp:setProperty property="*" name="msg" />
	<jsp:forward page="Mensajes3Guardar"></jsp:forward>
</c:if>

<c:if test="${ empty param.texto }">
	<jsp:forward page="enviar.jsp"></jsp:forward>
</c:if>

