package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.ComprobanteEntity;

public interface ComprobanteService {

    List<ComprobanteEntity> listar();

    List<ComprobanteEntity> buscar(String texto);

    ComprobanteEntity obtenerPorId(Integer id);

    ComprobanteEntity registrar(ComprobanteEntity comprobante);

    ComprobanteEntity actualizar(Integer id, ComprobanteEntity comprobante);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
