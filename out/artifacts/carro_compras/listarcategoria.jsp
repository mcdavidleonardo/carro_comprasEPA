<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Categorías</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <!-- Título -->
    <h1 class="text-center mb-4">Listado de Categorías</h1>

    <!-- Saludo -->
    <% if (username.isPresent()) { %>
    <div class="alert alert-primary">
        Hola <strong><%= username.get() %></strong>, bienvenido a la aplicación!
    </div>
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/categorias/form" class="btn btn-success">Ingresar nueva categoría</a>
    </div>
    <% } %>

    <!-- Tabla -->
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th>ID Categoría</th>
                <th>Nombre</th>
                <th>Estado</th>
                <%if(username.isPresent()){%>
                <th>Opciones</th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <% for (Categoria c : categorias) { %>
            <tr>
                <td><%= c.getIdCategoria() %></td>
                <td><%= c.getNombre() %></td>
                <td><%= c.getEstado() %></td>
                <%if(username.isPresent()){%>
                <td><a href="<%=request.getContextPath()%>/categorias/form?idCategoria=<%=c.getIdCategoria()%>">Editar</a></td>
                <%}%>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<div>
    <p><a href="<%=request.getContextPath()%>/index.html">Ir al inicio </a></p>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>