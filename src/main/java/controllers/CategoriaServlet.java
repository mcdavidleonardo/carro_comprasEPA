package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Categoria;
import models.Productos;
import service.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtener la conexión a la BDD
        Connection conn = (Connection) req.getAttribute("conn");
        //Crear el nuevo objeto
        CategoriaService servicios = new CategoriaServiceJdbcImpl(conn);
        List<Categoria> categorias = servicios.listar();

        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional=auth.getUsername(req);

        //Setear los atributos de categoria y username
        req.setAttribute("username", usernameOptional);
        req.setAttribute("categorias", categorias);

        //Redireccionar a la vista indicada listarcategoria.jsp
        getServletContext().getRequestDispatcher("/listarcategoria.jsp").forward(req, resp);
    }
}
