<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AdminControl</title>
<link rel="stylesheet" href="styles/admin.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>
	<div class="landing-page">
		<nav class="navbar navbar-expand-lg navbar-dark bg-custom">
			<div class="container">
				<!--<a class="navbar-brand" href="#">Minha Aplicação</a>  -->
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<!-- Itens do menu -->
				<div class="collapse navbar-collapse align-items-center justify-content-center text-center" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
						</li>
						<!--<li class="nav-item"><a class="nav-link" href="cadastro">Cadastro</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="relatorios">Relatorios</a>
						</li>-->
						<li class="nav-item"><a class="nav-link" href="lista">Lista</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="login.jsp">Logout</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="content">
			<div class="container">
				<div>
					<p class="big-text info italic text-center">
						Shoes<b>Happy</b> AdminControl
					</p>
				</div>
				<div class="image">
					<img
						src="https://img.r7.com/images/fotos-comprovam-que-cachorros-sao-os-pets-mais-engracados-20032018161340130">
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>
