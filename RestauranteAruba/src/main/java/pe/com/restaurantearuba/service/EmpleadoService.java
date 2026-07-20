package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.EmpleadoEntity;

public interface EmpleadoService {

    List<EmpleadoEntity> listar();

    List<EmpleadoEntity> buscar(String texto);

    EmpleadoEntity obtenerPorId(Integer id);

    EmpleadoEntity registrar(EmpleadoEntity empleado);

    EmpleadoEntity actualizar(Integer id, EmpleadoEntity empleado);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
