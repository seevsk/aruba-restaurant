package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Insumo;

public interface InsumoService {

    List<Insumo> listar();

    List<Insumo> buscar(String texto);

    Insumo obtenerPorId(Integer id);

    Insumo registrar(Insumo insumo);

    Insumo actualizar(Integer id, Insumo insumo);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
