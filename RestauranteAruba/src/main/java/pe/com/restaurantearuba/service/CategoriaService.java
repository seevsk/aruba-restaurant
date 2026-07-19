package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Categoria;

public interface CategoriaService {

    List<Categoria> listar();

    List<Categoria> buscar(String texto);

    Categoria obtenerPorId(Integer id);

    Categoria registrar(Categoria categoria);

    Categoria actualizar(Integer id, Categoria categoria);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
