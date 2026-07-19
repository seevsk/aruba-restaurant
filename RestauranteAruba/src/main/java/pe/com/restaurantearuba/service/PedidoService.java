package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Pedido;

public interface PedidoService {

    List<Pedido> listar();

    List<Pedido> buscar(String texto);

    Pedido obtenerPorId(Integer id);

    Pedido registrar(Pedido pedido);

    Pedido actualizar(Integer id, Pedido pedido);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
