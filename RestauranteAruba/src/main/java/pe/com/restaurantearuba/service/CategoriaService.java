package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.CategoriaEntity;

public interface CategoriaService {

    List<CategoriaEntity> listar();

    List<CategoriaEntity> buscar(String texto);

    CategoriaEntity obtenerPorId(Integer id);

    CategoriaEntity registrar(CategoriaEntity categoria);

    CategoriaEntity actualizar(Integer id, CategoriaEntity categoria);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
