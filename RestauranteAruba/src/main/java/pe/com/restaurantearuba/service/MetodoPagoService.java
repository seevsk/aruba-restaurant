package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.MetodoPago;

public interface MetodoPagoService {

    List<MetodoPago> listar();

    List<MetodoPago> buscar(String texto);

    MetodoPago obtenerPorId(Integer id);

    MetodoPago registrar(MetodoPago metodoPago);

    MetodoPago actualizar(Integer id, MetodoPago metodoPago);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
