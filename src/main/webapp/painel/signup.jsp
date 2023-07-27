<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastro</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="styles/cadastro.css" />
</head>
<body>
<header>
<a class="home-link" href="index.jsp">AdminPage</a>
</header>
  <div class="form-container">
    <h1>Cadastro no site</h1>
    <form method="post"  action="SignUpServlet">
	  		<div>
	    		<label>Matricula</label>
	    		<input type="text" name="usuario">
	  		</div>
	  		<div>
	    		<label>Nome</label>
	    		<input type="password" name="senha">
	  		</div>
	  		<div>
	    		<label>Endereço</label>
	    		<input type="password" name="senha">
	  		</div>
	  		<div>
	    		<label>Modalidade</label>
	    		<input type="password" name="senha">
	  		</div>
	  		<button type="submit">Cadastrar</button>
		</form>
  </div>
</body>
</html>