<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>Projeto JSP - Turma 2023</title>
</head>
<body>
	<body>
		<% 
		 String nome = request.getParameter("nome");
		 out.print(nome+"<br><br>");
		 
		 String senha = request.getParameter("senha");
		 out.print(senha+"<br><br>");
		%>
</body>
</html>