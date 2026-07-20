package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.PedidoEntity;

public interface PedidoService {

    List<PedidoEntity> listar();

    List<PedidoEntity> buscar(String texto);

    PedidoEntity obtenerPorId(Integer id);

    PedidoEntity registrar(PedidoEntity pedido);

    PedidoEntity actualizar(Integer id, PedidoEntity pedido);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
