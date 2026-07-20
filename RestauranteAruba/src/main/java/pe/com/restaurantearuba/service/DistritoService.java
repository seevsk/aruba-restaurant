package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.DistritoEntity;

public interface DistritoService {

    List<DistritoEntity> listar();

    List<DistritoEntity> buscar(String texto);

    DistritoEntity obtenerPorId(Integer id);

    DistritoEntity registrar(DistritoEntity distrito);

    DistritoEntity actualizar(Integer id, DistritoEntity distrito);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
