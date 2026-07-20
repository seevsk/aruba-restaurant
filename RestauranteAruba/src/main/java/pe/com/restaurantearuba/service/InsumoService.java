package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.InsumoEntity;

public interface InsumoService {

    List<InsumoEntity> listar();

    List<InsumoEntity> buscar(String texto);

    InsumoEntity obtenerPorId(Integer id);

    InsumoEntity registrar(InsumoEntity insumo);

    InsumoEntity actualizar(Integer id, InsumoEntity insumo);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
