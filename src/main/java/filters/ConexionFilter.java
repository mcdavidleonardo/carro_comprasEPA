package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceJdbcException;
import utils.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//Se crea una anotación
//Esta anotación sirve para poder utilizar la conexión en cualquier parte de la aplicación
@WebFilter("/*")
public class ConexionFilter implements Filter {

    /*Una clase Filter en Java es un objeto que realiza tareas de filtrado en las solicitudes
    o respuestas a un recurso. Los filtros se pueden ejecutar en servidores web compatibles
    con Jakarta EE. Los filtros interceptan solicitudes y respuestas de manera dinámica para
    transformar o utilizar la información que contienen. EL filtrado se realiza en el método
    doFilter
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
        Request: Petición que hace el cliente
        Response: Respuesta del servidor
        FilterChain: Es una clase de filtro que representa el flujo de procesamiento,
        llama al método chain.doFilter mediante los objetos(request, response), dentro
        de un filtro pasa la solicitud al siguiente filtro o al recurso destino(servlet o jsp)
         */
        //Obtener la conexión
        try(Connection conn = Conexion.getConnection()){
            //Verifica si la conexión realiza un autocommit(Configuración automática para cada instrucción SQL)
            if(conn.getAutoCommit()){
                //Si esta activa desactivamos el autocommit
                conn.setAutoCommit(false);
            }
            try{
                //Agregamos la conexión como un atributo en la solicitud,
                //esto permite que otros componentes(servlet o DAO) puedan acceder a la conexión
                //desde el objeto request
                servletRequest.setAttribute("conn", conn);
                //Pasa la solicitud y respuesta al siguiente filtro o al recurso destino(servlet o jsp)
                filterChain.doFilter(servletRequest, servletResponse);
                //Si el procesamiento se realizó correctamente sin lanzar excepciones, se confirma
                //la solicitud, y si aplica todos los cambios a la BDD.
                conn.commit();
                //Si ocurre algún error durante el procesamiento(dentro de doFilter), se captura la
                //excepción
            }catch(SQLException | ServiceJdbcException e){
                //Se deshacen los cambios con un rollback y de esa forma se mantiene la integridad de datos
                conn.rollback();
                //Enviamos un código de error HTTP 500 al cliente indicando un problema interno en el servidor
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
