package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.ProductoEntity;

public interface ProductoService {

    List<ProductoEntity> listar();

    List<ProductoEntity> buscar(String texto);

    ProductoEntity obtenerPorId(Integer id);

    ProductoEntity registrar(ProductoEntity producto);

    ProductoEntity actualizar(Integer id, ProductoEntity producto);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
