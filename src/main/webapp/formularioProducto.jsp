<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Productos productos = (Productos) request.getAttribute("productos");
    String operacion = (String) request.getAttribute("operacion");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingreso de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h1 class="text-center mb-4">Formulario Productos</h1>
    <form action="<%=request.getContextPath()%>/productos/form" method="post" class="needs-validation" novalidate>
        <% if (operacion.equals("E")) { %>
        <div class="alert alert-danger text-center" role="alert">
            <h3>¿Está seguro de eliminar el registro?</h3>
        </div>
        <% } %>

        <input type="hidden" name="operacion" value="<%=operacion%>">
        <input type="hidden" name="idProducto" value="<%=productos.getIdProducto()%>">

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre del Producto</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%=productos.getNombre() != null ? productos.getNombre() : ""%>" required>
            <div class="invalid-feedback">Por favor ingrese el nombre del producto.</div>
        </div>

        <div class="mb-3">
            <label for="categoria" class="form-label">Categoría</label>
            <select class="form-select" id="categoria" name="categoria" required>
                <option value="">--- Seleccione una Categoría ---</option>
                <% for (Categoria c : categorias) { %>
                <option value="<%=c.getIdCategoria()%>" <%=c.getIdCategoria().equals(productos.getCategoria().getIdCategoria()) ? "selected" : ""%>><%=c.getNombre()%></option>
                <% } %>
            </select>
            <div class="invalid-feedback">Por favor seleccione una categoría.</div>
        </div>

        <div class="mb-3">
            <label for="precio" class="form-label">Precio</label>
            <input type="number" class="form-control" id="precio" name="precio" step="0.01" value="<%=productos.getPrecio() != 0 ? productos.getPrecio() : ""%>" required>
            <div class="invalid-feedback">Por favor ingrese el precio del producto.</div>
        </div>

        <div class="mb-3">
            <label for="stock" class="form-label">Stock</label>
            <input type="number" class="form-control" id="stock" name="stock" step="1" value="<%=productos.getStock() != 0 ? productos.getStock() : ""%>" required>
            <div class="invalid-feedback">Por favor ingrese el stock del producto.</div>
        </div>

        <div class="d-flex justify-content-between">
            <button type="submit" class="btn <%=operacion.equals("U") ? "btn-success" : "btn-danger"%>">
                <%=operacion.equals("U") ? "Guardar" : "Eliminar"%>
            </button>
            <a href="<%=request.getContextPath()%>/index.html" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Bootstrap validation
    (function () {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>
</body>
</html>
