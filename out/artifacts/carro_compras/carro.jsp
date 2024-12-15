<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="models.*" %>
<%
    Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carro de compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h1 class="text-center mb-4">Carro de compras</h1>
    <% if (carro == null || carro.getItems().isEmpty()) { %>
    <div class="alert alert-warning text-center" role="alert">
        Lo sentimos, no hay productos en el carro de compras!
    </div>
    <% } else { %>
    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID PRODUCTO</th>
            <th>NOMBRE</th>
            <th>PRECIO</th>
            <th>CANTIDAD</th>
            <th>TOTAL</th>
        </tr>
        </thead>
        <tbody>
        <% for (ItemCarro item : carro.getItems()) { %>
        <tr>
            <td><%= item.getProductos().getIdProducto() %></td>
            <td><%= item.getProductos().getNombre() %></td>
            <td><%= item.getProductos().getPrecio() %></td>
            <td><%= item.getCantidad() %></td>
            <td><%= item.getSbtotal() %></td>
        </tr>
        <% } %>
        <tr>
            <td colspan="4" class="text-end fw-bold">Total</td>
            <td class="fw-bold"><%= carro.getTotal() %></td>
        </tr>
        </tbody>
    </table>
    <% } %>
    <div class="d-flex justify-content-between">
        <a href="<%= request.getContextPath() %>/productos" class="btn btn-primary">Seguir comprando</a>
        <a href="<%= request.getContextPath() %>/index.html" class="btn btn-secondary">Ir al inicio</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
