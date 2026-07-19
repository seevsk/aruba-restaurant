package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.Empleado;
import pe.com.restaurantearuba.repository.EmpleadoRepository;
import pe.com.restaurantearuba.service.EmpleadoService;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return empleadoRepository.buscar(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado obtenerPorId(Integer id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado: " + id));
    }

    @Override
    public Empleado registrar(Empleado empleado) {
        empleado.setId(null);
        if (empleado.getEstado() == null) {
            empleado.setEstado(true);
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado actualizar(Integer id, Empleado datos) {
        Empleado existente = obtenerPorId(id);
        existente.setNumeroDocumento(datos.getNumeroDocumento());
        existente.setNombres(datos.getNombres());
        existente.setApellidoPaterno(datos.getApellidoPaterno());
        existente.setApellidoMaterno(datos.getApellidoMaterno());
        existente.setCargo(datos.getCargo());
        existente.setTelefono(datos.getTelefono());
        existente.setDireccion(datos.getDireccion());
        existente.setDistrito(datos.getDistrito());
        existente.setTipoDocumento(datos.getTipoDocumento());
        return empleadoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        empleadoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        Empleado entidad = obtenerPorId(id);
        entidad.setEstado(true);
        empleadoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        Empleado entidad = obtenerPorId(id);
        entidad.setEstado(false);
        empleadoRepository.save(entidad);
    }
}
