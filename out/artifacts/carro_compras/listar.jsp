<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,models.*" %>
<%
    List<Productos> productos = (List<Productos>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h1 class="text-center mb-4">Listado de Productos</h1>
    <% if (username.isPresent()) { %>
    <div class="alert alert-primary" role="alert">
        Hola <strong><%= username.get() %></strong>, bienvenido a la aplicación!
    </div>
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/productos/form?operacion=U" class="btn btn-success">Ingrese el producto</a>
    </div>
    <% } %>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th scope="col">ID Producto</th>
            <th scope="col">Nombre</th>
            <th scope="col">Categoría</th>
            <% if (username.isPresent()) { %>
            <th scope="col">Precio</th>
            <th scope="col">Stock</th>
            <th scope="col">Opciones</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <% for (Productos p : productos) { %>
        <tr>
            <td><%= p.getIdProducto() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getCategoria().getNombre() %></td>
            <% if (username.isPresent()) { %>
            <td><%= p.getPrecio() %></td>
            <td><%= p.getStock() %></td>
            <td>
                <% if (p.getStock() > 0) { %>
                <a href="<%= request.getContextPath() %>/agregar-carro?idProducto=<%= p.getIdProducto() %>" class="btn btn-primary btn-sm">Agregar</a>
                <% } %>
                <a href="<%= request.getContextPath() %>/productos/form?idProducto=<%= p.getIdProducto() %>&operacion=U" class="btn btn-warning btn-sm">Editar</a>
                <a href="<%= request.getContextPath() %>/productos/form?idProducto=<%= p.getIdProducto() %>&operacion=E" class="btn btn-danger btn-sm">Eliminar</a>
            </td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
