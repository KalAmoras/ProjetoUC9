<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastro de Clientes</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="styles.css" />
</head>
<body>
  <div class="form-container">
    <h1>Cadastro de Novo Cliente</h1>
    <form>
      <input type="text" placeholder="Nome completo" required>
      <input type="text" placeholder="Endereco" required>
      <input type="text" placeholder="Modalidade" required>
      <button class="submit-button" type="submit">Cadastrar <i class="fas fa-check"></i></button>
    </form>
  </div>
</body>
</html>
