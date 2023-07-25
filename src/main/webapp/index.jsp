<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<title>Projeto JSP - Turma 2023</title>
</head>
<body>
  <div class="items">
    <h1>Sistema de Cadastro de Clientes</h1>
    <h2>O que você deseja fazer com os clientes?</h2>
    <input type="text" placeholder="Input temporário para testes aleatórios">
    <button>Testar</button>
    <div class="button-container">
      <button onClick="window.open('cadastro.jsp','cadastro','resizable, height=450, width=450'); return false;">cadastrar</button>
      <button>editar</button>
      <button>listar</button>
      <button>excluir</button>
    </div>
  </div>
</body>
</html>