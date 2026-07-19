package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.Empleado;

public interface EmpleadoService {

    List<Empleado> listar();

    List<Empleado> buscar(String texto);

    Empleado obtenerPorId(Integer id);

    Empleado registrar(Empleado empleado);

    Empleado actualizar(Integer id, Empleado empleado);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
