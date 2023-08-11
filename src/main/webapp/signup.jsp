<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="styles/login.css" />
</head>
<body>

<header>
<a class="home-link" href="index.jsp">Home</a>
</header>
  <div class="form-container">
    <h1>Cadastro</h1>
    <form action="<%= request.getContextPath() %>/UsuarioServlet" method="post">
		<div>
	    		<label>Usuario</label>
	    		<input required type="text" name="usuario">
	  		</div>
	  		<div>
	    		<label>Senha</label>
	    		<input required type="password" name="senha">
	  		</div>
		<button type="submit" value="Salvar">Cadastrar</button>
	</form>
	<div>
		<h4 id="mensagem">${mensagem}</h4>
	</div>
  </div>
</body>
</html>