package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Categoria;
import models.Productos;
import service.CategoriaService;
import service.CategoriaServiceJdbcImpl;
import service.ProductoService;
import service.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/categorias/form")
public class CategoriaFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Presentar el formulario
        //Obtener la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImpl(conn);
        req.setAttribute("categorias", service.listar());
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImpl(conn);
        String nombre = req.getParameter("nombre");
        int estado;
        try{
            estado = Integer.valueOf(req.getParameter("estado"));
        } catch (NumberFormatException e) {
            estado = 0;
        }
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setEstado(estado);
        service.guardar(categoria);
        //Redireccionar a un listado para que no se ejecute el método doPost
        //nuevamente y se guarde los datos duplicados
        resp.sendRedirect(req.getContextPath() + "/categorias");
    }
}
