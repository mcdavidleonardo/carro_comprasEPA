package repositories;

import java.sql.SQLException;
import java.util.List;

/*
 * <T> Es un parámetro genérico que permite que la interfaz sea utilizada
 * como se desee o cualquier tipo de objeto(entidad)
 * Esto hace que la interfaz sea flexible y reutilizable para cualquier
 * tipo de dato
 * */
public interface Repository<T> {
    /*El método listar retorna una lista de objetos de tipo generico T
     * Se usa para obtener todos los registros de una entidad desde la BDD
     * */
    List<T> listar() throws SQLException;

    /*El método porId recibe un identificador único y retorna un objeto de tipo T
     * correspondiente a ese identificador.
     * Se usa para buscar un registro específico por su ID*/
    T porId(Long id) throws SQLException;

    /*Recibe un objeto de tipo T y lo guarda en la BDD
     * Este método puede ser utilizado para crear o actualizar un registro
     * dependiendo si el objeto ya existe en la BDD*/
    void guardar(T t) throws SQLException;

    /*Recibe un identificador único y si existe el identificador lo borra de la BDD*/
    void eliminar(Long id) throws SQLException;

    void actualizarStock(Long id) throws SQLException;
}
