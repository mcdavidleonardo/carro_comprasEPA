package service;

import models.Categoria;
import models.Productos;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    void guardar(Categoria categoria);
    Optional<Categoria> agregarPorId(Long idCategoria);
}
