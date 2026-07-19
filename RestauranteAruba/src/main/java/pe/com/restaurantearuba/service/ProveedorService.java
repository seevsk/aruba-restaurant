package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Proveedor;

public interface ProveedorService {

    List<Proveedor> listar();

    List<Proveedor> buscar(String texto);

    Proveedor obtenerPorId(Integer id);

    Proveedor registrar(Proveedor proveedor);

    Proveedor actualizar(Integer id, Proveedor proveedor);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
