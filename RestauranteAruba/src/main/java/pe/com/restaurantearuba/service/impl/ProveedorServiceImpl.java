package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.ProveedorEntity;
import pe.com.restaurantearuba.repository.ProveedorRepository;
import pe.com.restaurantearuba.service.ProveedorService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorEntity> listar() {
        return proveedorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return proveedorRepository.findByEstadoTrue();
        }
        return proveedorRepository.buscarHabilitados(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorEntity obtenerPorId(Integer id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado: " + id));
    }

    @Override
    public ProveedorEntity registrar(ProveedorEntity proveedor) {
        proveedor.setId(null);
        if (proveedor.getEstado() == null) {
            proveedor.setEstado(true);
        }
        return proveedorRepository.save(proveedor);
    }

    @Override
    public ProveedorEntity actualizar(Integer id, ProveedorEntity datos) {
        ProveedorEntity existente = obtenerPorId(id);
        existente.setRuc(datos.getRuc());
        existente.setRazonSocial(datos.getRazonSocial());
        existente.setApellidoPaterno(datos.getApellidoPaterno());
        existente.setApellidoMaterno(datos.getApellidoMaterno());
        existente.setTelefono(datos.getTelefono());
        existente.setCorreo(datos.getCorreo());
        return proveedorRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        proveedorRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        ProveedorEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        proveedorRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        ProveedorEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        proveedorRepository.save(entidad);
    }
}
