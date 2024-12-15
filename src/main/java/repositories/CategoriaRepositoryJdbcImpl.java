package repositories;

import models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImpl implements Repository<Categoria>{
    private Connection conn;

    public CategoriaRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias");
            while(rs.next()) {
                Categoria c = getCategoria(rs);
                categorias.add(c);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long idCategoria) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement stmt = conn.prepareStatement(
                "select * from categorias where idcategoria = ?")) {
            stmt.setLong(1, idCategoria);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        if(categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0){
            sql = "update categorias set nombre = ?, estado = ? where idcategoria = ?";
        }else{
            sql = "insert into categorias (nombre, estado) values (?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getEstado());
            if(categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0){
                stmt.setLong(3, categoria.getIdCategoria());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public void actualizarStock(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setNombre(rs.getString("nombre"));
        c.setEstado(rs.getInt("estado"));
        c.setIdCategoria(rs.getLong("idcategoria"));
        return c;
    }
}
