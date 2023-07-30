<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="controller.ListClientServlet"%>
<%@ page import="controller.NewClientServlet"%>
<%@ page import="models.Cliente"%>
<%@ page import="dao.ClienteDAO"%>
<%@ page import="connection.ConnectionMySQL"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Editar Cliente: </h1>
	<form action="editar_cliente" method="post">
		<input type="hidden" name="matricula" value="${cliente.matricula}">
        <label for="nome">Cliente:</label>
        <input type="text" id="nome" name="nome" value="${cliente.nome}" required>
        <label for="endereco">Endereço:</label>
        <input type="text" id="endereco" name="endereco" value="${cliente.endereco}" required>
        <label for="modalidade">Modalidade:</label>
        <input type="text" id="modalidade" name="modalidade" value="${cliente.modalidade}" required>
		<button type="submit">Editar Cliente</button>
	</form>
</body>
</html>