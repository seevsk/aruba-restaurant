package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Distrito;

public interface DistritoService {

    List<Distrito> listar();

    List<Distrito> buscar(String texto);

    Distrito obtenerPorId(Integer id);

    Distrito registrar(Distrito distrito);

    Distrito actualizar(Integer id, Distrito distrito);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
