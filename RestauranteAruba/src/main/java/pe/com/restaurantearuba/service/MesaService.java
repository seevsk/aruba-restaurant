package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Mesa;

public interface MesaService {

    List<Mesa> listar();

    List<Mesa> buscar(String texto);

    Mesa obtenerPorId(Integer id);

    Mesa registrar(Mesa mesa);

    Mesa actualizar(Integer id, Mesa mesa);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
