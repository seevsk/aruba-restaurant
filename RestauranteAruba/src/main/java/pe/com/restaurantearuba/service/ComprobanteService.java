package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Comprobante;

public interface ComprobanteService {

    List<Comprobante> listar();

    List<Comprobante> buscar(String texto);

    Comprobante obtenerPorId(Integer id);

    Comprobante registrar(Comprobante comprobante);

    Comprobante actualizar(Integer id, Comprobante comprobante);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
