package service;

import models.Categoria;
import models.Productos;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listar();
    void guardar(Categoria categoria);
}
