<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
  Categoria categoria = (Categoria) request.getAttribute("categoria");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ingreso de Categorías</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center mb-4">Formulario Categorías</h1>
  <div class="card shadow p-4">
    <form action="<%=request.getContextPath()%>/categorias/form" method="post">
      <!-- Nombre -->
      <div class="mb-3">
        <input type="hidden" name="idCategoria" value="<%=categoria.getIdCategoria()%>">
        <label for="nombre" class="form-label">Ingrese el nombre de la categoría</label>
        <input type="text" id="nombre" name="nombre" value="<%=categoria.getNombre() != null?categoria.getNombre():""%>" class="form-control" placeholder="Nombre de la categoría" required>
      </div>
      <!-- Estado -->
      <div class="mb-3">
        <label for="estado" class="form-label">Ingrese el estado de la categoría</label>
        <select id="estado" name="estado" class="form-select" required>
          <%
            int estado = categoria.getEstado();
          %>
          <option value="" <%=(estado==0)? "selected":""%>>--- Seleccione un estado ---</option>
          <option value="1" <%=(estado==1)? "selected":""%>>Activa</option>
          <option value="2" <%=(estado==2)? "selected":""%>>Cancelada</option>
        </select>
      </div>
      <!-- Botón Enviar -->
      <div class="d-grid">
        <button type="submit" class="btn btn-primary">Enviar</button>
      </div>
    </form>
  </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>