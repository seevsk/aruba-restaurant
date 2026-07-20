package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.DetallePedidoEntity;

public interface DetallePedidoService {

    List<DetallePedidoEntity> listar();

    List<DetallePedidoEntity> buscar(String texto);

    DetallePedidoEntity obtenerPorId(Integer id);

    DetallePedidoEntity registrar(DetallePedidoEntity detallePedido);

    DetallePedidoEntity actualizar(Integer id, DetallePedidoEntity detallePedido);

    void eliminar(Integer id);
}
