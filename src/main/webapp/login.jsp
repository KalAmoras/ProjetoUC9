<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastro de Clientes</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="styles/login.css" />
</head>
<body>
<header>
<a class="home-link" href="index.jsp">Home</a>
</header>
	
  <div class="form-container">
    <h1>Login</h1>
    <form method="post" action="LoginServlet">
	  		<div>
	    		<label>Usuário</label>
	    		<input type="text" name="usuario">
	  		</div>
	  		<div>
	    		<label>Senha</label>
	    		<input type="password" name="senha">
	  		</div>
	  		<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
	  		<button type="submit">Login</button>
		</form>
		<h4>${mensagem}</h4>
  </div>
</body>
</html>