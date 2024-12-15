package repositories;

import models.Categoria;
import models.Productos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Productos> {
    /*Conexión a la BDD, la conexión se tiene que pasar al Repository,
    luego se lo pasa al Service y a su vez el servlet lo obtiene del objeto
    request de los atributos que se setearon por request. Una vez obtenida
    la conexión vuelve a pasar al Service y del Service al Repository
    * */
    private Connection conn;

    //Constructor
    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    // Se sobreescriben los métodos de la clase interfaz
    @Override
    public List<Productos> listar() throws SQLException {
        List<Productos> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select p.*, c.nombre as categoria from productos as p" +
                     " inner join categorias as c on (p.idcategoria=c.idcategoria) order by p.idproducto")) {
            while (rs.next()) {
                Productos p = getProductos(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Productos porId(Long idProducto) throws SQLException {
        Productos productos = null;
        try (PreparedStatement stmt = conn.prepareStatement("select p.*, c.nombre as categoria from productos as p" +
                " inner join categorias as c on (p.idcategoria=c.idcategoria)" +
                " where p.idproducto = ?")) {
            stmt.setLong(1, idProducto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    productos = getProductos(rs);
                }
            }
        }
        return productos;
    }

    @Override
    public void guardar(Productos productos) throws SQLException {
        String sql;
        if(productos.getIdProducto() != null && productos.getIdProducto() > 0){
            sql = "update productos set idcategoria = ?, nombre = ?, precio = ?, stock = ? where idproducto = ?";
        }else{
            sql = "insert into productos (idcategoria, nombre, precio, stock) values (?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, productos.getCategoria().getIdCategoria());
            stmt.setString(2, productos.getNombre());
            stmt.setDouble(3, productos.getPrecio());
            stmt.setInt(4, productos.getStock());
            if(productos.getIdProducto() != null && productos.getIdProducto() > 0){
                stmt.setLong(5, productos.getIdProducto());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long idProducto) throws SQLException {
        String sql = "delete from productos where idproducto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idProducto);
            stmt.executeUpdate();
        }
    }

    @Override
    public void actualizarStock(Long id) throws SQLException {
        String sql;
        sql = "update productos set stock = stock - 1 where idproducto = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Productos getProductos(ResultSet rs) throws SQLException {
        Productos p = new Productos();
        p.setIdProducto(rs.getLong("idproducto"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getLong("idcategoria"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}
