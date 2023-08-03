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
<link rel="stylesheet" href="styles/lista.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<title>Lista</title>
</head>
<body>
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
			<div
				class="collapse navbar-collapse align-items-center justify-content-center text-center"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Logout</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="main-container">
		<div class="add-cliente" id="formulario-adicionar">
			<h1 class="main-heading">Adicionar Novo Cliente</h1>
			<form action="lista" method="post" class="adicionar-form">
				<div class="form-group">
					<label for="nome">Cliente:</label> <input type="text"
						maxlength="45" id="nome" name="nome" required>
				</div>
				<div class="form-group">
					<label for="endereco">Endereço:</label> <input type="text"
						maxlength="45" id="endereco" name="endereco" required>
				</div>
				<div class="form-group">
					<label for="modalidade">Modalidade:</label> <input type="text"
						maxlength="45" id="modalidade" name="modalidade" required>
				</div>
				<div class="justify-content-center d-flex">
					<button type="submit" class="btn-add mt-3 mb-3">
						<i class="fas fa-user-plus" style="font-size: 48px;"></i>
					</button>
				</div>
			</form>
		</div>
		<div style="padding: 20px;">
					<button type="submit" class="btn-add" id="btnMostrarForm">
						<i class="fas fa-user-plus" style="font-size: 48px;"></i>

					</button>
				</div>
		<div class="lista-clientes">
			<div>
				<h1 class="main-heading">Lista de Clientes</h1>
				<div class="table-container">
					<table>
						<tr>
							<th class="text-center">Matrícula</th>
							<th class="text-center">Nome</th>
							<th class="text-center">Endereço</th>
							<th class="text-center">Modalidade</th>
							<th class="text-center d-none d-sm-table-cell">Editar</th>
							<th class="text-center d-none d-sm-table-cell">Deletar</th>
						</tr>

						<%
						List<Cliente> clientes = new ClienteDAO().getAllClients();
						for (Cliente cliente : clientes) {
						%>
						<tr class="borda-tr">
							<td class="text-center align-middle"><span><%=cliente.getMatricula()%></span></td>
							<td class="text-center align-middle"><span class="editable"
								data-field="nome"><%=cliente.getNome()%></span></td>
							<td class="text-center align-middle"><span class="editable"
								data-field="endereco"><%=cliente.getEndereco()%></span></td>
							<td class="text-center align-middle"><span class="editable"
								data-field="modalidade"><%=cliente.getModalidade()%></span></td>
							<td
								class="text-center align-middle d-none d-sm-table-cell d-flex justify-content-center">
								<button class="btn btn-warning editar-cliente"
									data-id="<%=cliente.getMatricula()%>">
									<i class="fas fa-edit"></i>
								</button>
							</td>
							<td
								class="text-center align-middle d-none d-sm-table-cell d-flex justify-content-center">
								<form class="d-flex justify-content-center"
									action="deletar_cliente" method="post">
									<input type="hidden" name="matricula"
										value="<%=cliente.getMatricula()%>">
									<button type="submit" class="btn btn-danger">
										<i class="fas fa-trash-alt"></i>
									</button>
								</form>
							</td>
						</tr>
						<%
						}
						%>
					</table>
				</div>
			</div>
		</div>
	</main>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
		// Script para controlar a exibição da div e a animação
		document.addEventListener("DOMContentLoaded", function() {
			const btnMostrarForm = document.getElementById("btnMostrarForm");
			const formularioAdicionar = document
					.getElementById("formulario-adicionar");

			btnMostrarForm.addEventListener("click", function() {
				formularioAdicionar.classList.toggle("visible");
				formularioAdicionar.classList.toggle("fade-in");
				btnMostrarForm.classList.toggle("active");
			});
		});
	</script>

	<script>
    $(document).ready(function () {
        $(".editar-cliente").on("click", function () {
            var clienteId = $(this).data("id");
            var tr = $(this).closest("tr");

            tr.find(".editable").each(function () {
                var valor = $(this).text();
                var campo = $(this).data("field");
                $(this).html("<input style='width: 300px;' type='text' name='" + campo + "' value='" + valor + "'>");
            });

            tr.find(".editar-cliente").replaceWith("<button class='btn btn-success salvar-cliente' data-id='" + clienteId + "'><i class='fas fa-save'></i></button>");
        });
        $(document).on("click", ".salvar-cliente", function () {
            var clienteId = $(this).data("id");
            var tr = $(this).closest("tr");
            var nome = tr.find("[data-field='nome'] input").val();
            var endereco = tr.find("[data-field='endereco'] input").val();
            var modalidade = tr.find("[data-field='modalidade'] input").val();

            $.ajax({
                type: "POST",
                url: "editar_cliente",
                data: {
                    matricula: clienteId,
                    nome: nome,
                    endereco: endereco,
                    modalidade: modalidade
                },
                success: function () {
                    tr.find(".editable[data-field='nome']").html(nome);
                    tr.find(".editable[data-field='endereco']").html(endereco);
                    tr.find(".editable[data-field='modalidade']").html(modalidade);
                    tr.find(".salvar-cliente").replaceWith("<button class='btn btn-warning editar-cliente' data-id='" + clienteId + "'><i class='fas fa-edit'></i></button>");
                    tr.find(".btn-danger").show();
                    location.reload();
                }
            });
        });
    });
</script>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>