<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>List of Users</h1>
    <ul>
        <%-- Iterate through the list of users passed from the Servlet --%>
        <%  %>
        <c:forEach items="${clients}" var="clientes">
            <li>
                <span>${clients.nome} (${clients.matricula})</span>
                <a href="EditUserServlet?id=${clients.matricula}">Edit</a>
                <a href="DeleteUserServlet?id=${clients.matricula}">Delete</a>
            </li>
        </c:forEach>
    </ul>
    <h2>Adicionar novo cliente:</h2>
    <form action="NewClientServlet" method="post">
        <label for="username">Matricula:</label>
        <input type="text" id="username" name="matricula" required>
        
        <label for="nome">Cliente:</label>
        <input type="text" id="nome" name="nome" required>
        
        <label for="endereco">Endereço:</label>
        <input type="text" id="endereco" name="endereco" required>
        
        <label for="modalidade">Modalidade:</label>
        <input type="text" id="modalidade" name="modalidade" required>

        <button type="submit">Adicionar Cliente</button>
    </form>
</body>
</html>