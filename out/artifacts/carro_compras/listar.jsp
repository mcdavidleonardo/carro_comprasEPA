<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,models.*" %>
<%
List<Productos> productos = (List<Productos>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
    <title>Lista de productos</title>
</head>
<body>
<h1>Listado de Productos</h1>
<%if(username.isPresent()){%>
    <div style="color: blue;">Hola <%=username.get()%>, bienvenido a la aplicación!</div>
    <div><p><a href="${pageContext.request.contextPath}/productos/form">Ingrese el producto</a></p></div>
<%}%>
<table>
    <tr>
        <th>ID Producto</th>
        <th>Nombre</th>
        <th>Categoría</th>
        <%if(username.isPresent()){%>
        <th>Precio</th>
        <th>Opciones</th>
        <%}%>
    </tr>
    <%
        for(Productos p: productos){%>
    <tr>
        <td><%=p.getIdProducto()%></td>
        <td><%=p.getNombre()%></td>
        <td><%=p.getCategoria()%></td>
        <%if(username.isPresent()){%>
        <td><%=p.getPrecio()%></td>
        <td><a href="<%=request.getContextPath()%>/agregar-carro?idProducto=<%=p.getIdProducto()%>">Agregar</a></td>
    <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
