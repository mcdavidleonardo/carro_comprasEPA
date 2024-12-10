package service;

import models.Categoria;
import repositories.CategoriaRepositoryJdbcImpl;
import repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoriaServiceJdbcImpl implements CategoriaService {
    private Repository<Categoria> repositoryCategoriaJdbc;

    public CategoriaServiceJdbcImpl(Connection connection) {
        repositoryCategoriaJdbc = new CategoriaRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Categoria> listar() {
        try{
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Categoria categoria) {
        try {
            repositoryCategoriaJdbc.guardar(categoria);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}