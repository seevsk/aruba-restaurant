package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.ProveedorEntity;

public interface ProveedorService {

    List<ProveedorEntity> listar();

    List<ProveedorEntity> buscar(String texto);

    ProveedorEntity obtenerPorId(Integer id);

    ProveedorEntity registrar(ProveedorEntity proveedor);

    ProveedorEntity actualizar(Integer id, ProveedorEntity proveedor);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
