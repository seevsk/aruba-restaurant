package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.MetodoPagoEntity;

public interface MetodoPagoService {

    List<MetodoPagoEntity> listar();

    List<MetodoPagoEntity> buscar(String texto);

    MetodoPagoEntity obtenerPorId(Integer id);

    MetodoPagoEntity registrar(MetodoPagoEntity metodoPago);

    MetodoPagoEntity actualizar(Integer id, MetodoPagoEntity metodoPago);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
