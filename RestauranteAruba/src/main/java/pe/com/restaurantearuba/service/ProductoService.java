package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Producto;

public interface ProductoService {

    List<Producto> listar();

    List<Producto> buscar(String texto);

    Producto obtenerPorId(Integer id);

    Producto registrar(Producto producto);

    Producto actualizar(Integer id, Producto producto);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
