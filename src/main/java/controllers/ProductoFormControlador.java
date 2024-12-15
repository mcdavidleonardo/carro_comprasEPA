package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Categoria;
import models.Productos;
import service.ProductoService;
import service.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Presentar el formulario
        //Obtener la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        req.setAttribute("categorias", service.listarCategorias());
        String operacion = req.getParameter("operacion");
        long id;
        try{
            id = Long.parseLong(req.getParameter("idProducto"));
        }catch(NumberFormatException e){
            id = 0L;
        }
        Productos productos = new Productos();
        productos.setCategoria(new Categoria());
        if(id > 0){
            Optional<Productos> o = service.agregarPorId(id);
            if(o.isPresent()){
                productos = o.get();
            }
        }
        req.setAttribute("productos", productos);
        req.setAttribute("operacion", operacion);
        getServletContext().getRequestDispatcher("/formularioProducto.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        String nombre = req.getParameter("nombre");
        Double precio;
        try{
            precio = Double.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0.0;
        }
        Long idCategoria;
        try{
            idCategoria = Long.valueOf(req.getParameter("categoria"));
        }catch(NumberFormatException e){
            idCategoria = 0L;
        }

        //Obtener el idProducto
        long idProducto;
        try{
            idProducto = Long.parseLong(req.getParameter("idProducto"));
        }catch (NumberFormatException e){
            idProducto = 0L;
        }

        //Obtener el Stock
        int stock;
        try{
            stock = Integer.valueOf(req.getParameter("stock"));
        } catch (NumberFormatException e) {
            stock = 0;
        }

        //Obtener la operacion a ejecutar
        String operacion;
        try{
            operacion = req.getParameter("operacion");
        }catch (NumberFormatException e){
            operacion = "";
        }

        Productos productos = new Productos();
        productos.setIdProducto(idProducto);
        productos.setNombre(nombre);
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        productos.setCategoria(categoria);
        productos.setPrecio(precio);
        productos.setStock(stock);
        if(operacion.equals("U")){
            service.guardar(productos);
        }
        if(operacion.equals("E")){
            service.eliminar(idProducto);
        }
        //Redireccionar a un listado para que no se ejecute el método doPost
        //nuevamente y se guarde los datos duplicados
        resp.sendRedirect(req.getContextPath() + "/productos");

    }
}
