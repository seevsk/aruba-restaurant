package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.DetallePedido;

public interface DetallePedidoService {

    List<DetallePedido> listar();

    List<DetallePedido> buscar(String texto);

    DetallePedido obtenerPorId(Integer id);

    DetallePedido registrar(DetallePedido detallePedido);

    DetallePedido actualizar(Integer id, DetallePedido detallePedido);

    void eliminar(Integer id);
}
