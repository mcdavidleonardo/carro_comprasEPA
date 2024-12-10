package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Productos;
import service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Obtener la conexión a la BDD
        Connection conn = (Connection) req.getAttribute("conn");
        //Crear el nuevo objeto
        ProductoService servicios = new ProductoServiceJdbcImpl(conn);
        List<Productos> productos = servicios.listar();

        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional=auth.getUsername(req);

        //Setear los atributos de producto y username
        req.setAttribute("username", usernameOptional);
        req.setAttribute("productos", productos);

        //Redireccionar a la vista indicada listar.jsp
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
