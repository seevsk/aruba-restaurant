package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.ClienteEntity;

public interface ClienteService {

    List<ClienteEntity> listar();

    List<ClienteEntity> buscar(String texto);

    ClienteEntity obtenerPorId(Integer id);

    ClienteEntity registrar(ClienteEntity cliente);

    ClienteEntity actualizar(Integer id, ClienteEntity cliente);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
