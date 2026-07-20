package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.MesaEntity;

public interface MesaService {

    List<MesaEntity> listar();

    List<MesaEntity> buscar(String texto);

    MesaEntity obtenerPorId(Integer id);

    MesaEntity registrar(MesaEntity mesa);

    MesaEntity actualizar(Integer id, MesaEntity mesa);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
