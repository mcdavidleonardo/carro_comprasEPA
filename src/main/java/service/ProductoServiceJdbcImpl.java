package service;

import models.Categoria;
import models.Productos;
import repositories.CategoriaRepositoryJdbcImpl;
import repositories.ProductoRepositoryJdbcImpl;
import repositories.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService{
    //private ProductoRepositoryJdbcImpl repositoryJdbc;
    private Repository<Productos> repositoryJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public ProductoServiceJdbcImpl(Connection connection) {
        repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        repositoryCategoriaJdbc = new CategoriaRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Productos> listar() {
        try{
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Productos> agregarPorId(Long idProducto) {
        try{
            return Optional.ofNullable(repositoryJdbc.porId(idProducto));
        }catch(SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Productos producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void actualizarStock(Long id) {
        try {
            repositoryJdbc.actualizarStock(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long idCategoria) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(idCategoria));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
