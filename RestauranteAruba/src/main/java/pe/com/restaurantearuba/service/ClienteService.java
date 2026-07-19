package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Cliente;

public interface ClienteService {

    List<Cliente> listar();

    List<Cliente> buscar(String texto);

    Cliente obtenerPorId(Integer id);

    Cliente registrar(Cliente cliente);

    Cliente actualizar(Integer id, Cliente cliente);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
