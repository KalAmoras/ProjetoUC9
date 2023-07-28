<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%--<%! int month=2; 
	<% if(month==2){ %>
	<a>Its February</a>
	<% }else{ %>
	<a>Any month other than February</a>
	<%} %> --%>


	<h1>Lista de Clientes</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Endereço</th>
			<th>Modalidade</th>
			<th>Editar</th>
			<th>Deletar</th>
		</tr>

		<%
		List<Cliente> clientes = new ClienteDAO().getAllClients();
		for (Cliente cliente : clientes) {
		%>
		<tr>
			<td><%=cliente.getMatricula()%></td>
			<td><%=cliente.getNome()%></td>
			<td><%=cliente.getEndereco()%></td>
			<td><%=cliente.getModalidade()%></td>
			<td><a href="edit-client?id=<%=cliente.getMatricula()%>">Edit</a></td>
			<td><a href="delete-client?id=<%=cliente.getMatricula()%>">Delete</a></td>
		</tr>
		<%
		}
		%>
	</table>
	<br>

	<a href="new-client">Add New Client</a>

	<h2>Adicionar novo cliente:</h2>
	<form action="NewClientServlet" method="post">
		<label for="matricula">Matricula:</label> <input type="text"
			id="matricula" name="matricula" required> <label for="nome">Cliente:</label>
		<input type="text" id="nome" name="nome" required> <label
			for="endereco">Endereço:</label> <input type="text" id="endereco"
			name="endereco" required> <label for="modalidade">Modalidade:</label>
		<input type="text" id="modalidade" name="modalidade" required>

		<button type="submit">Adicionar Cliente</button>
	</form>
</body>
</html>