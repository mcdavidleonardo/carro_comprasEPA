<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<html>
<head>
    <title>Ingreso de Productos</title>
</head>
<body>
<h1> Formulario Productos</h1>
<div>
    <form action="<%=request.getContextPath()%>/productos/form" method="post">
        <div>
            <label for="nombre">Ingrese el nombre del producto</label>
            <div>
                <input type="text" id="nombre" name="nombre">
            </div>
        </div>
        <div>
            <label for="categoria">Ingrese la categoría del producto</label>
            <div>
                <select id="categoria" name="categoria">
                    <option value="">--- Seleccione una Categoría</option>
                    <%for (Categoria c : categorias) {%>
                    <option value="<%=c.getIdCategoria()%>"><%=c.getNombre()%></option>
                    <%}%>
                </select>
            </div>
        </div>
        <div>
            <label for="precio">Ingrese el precio del producto</label>
            <div>
                <input type="number" id="precio" name="precio" step="0.01">
            </div>
        </div>
        <div>
            <input type="submit" value="Enviar">
        </div>
    </form>
</div>

</body>
</html>
