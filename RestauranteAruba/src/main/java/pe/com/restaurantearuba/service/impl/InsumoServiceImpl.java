package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.InsumoEntity;
import pe.com.restaurantearuba.repository.InsumoRepository;
import pe.com.restaurantearuba.service.InsumoService;

@Service
@RequiredArgsConstructor
@Transactional
public class InsumoServiceImpl implements InsumoService {

    private final InsumoRepository insumoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InsumoEntity> listar() {
        return insumoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InsumoEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return insumoRepository.findByNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public InsumoEntity obtenerPorId(Integer id) {
        return insumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Insumo no encontrado: " + id));
    }

    @Override
    public InsumoEntity registrar(InsumoEntity insumo) {
        insumo.setId(null);
        if (insumo.getEstado() == null) {
            insumo.setEstado(true);
        }
        return insumoRepository.save(insumo);
    }

    @Override
    public InsumoEntity actualizar(Integer id, InsumoEntity datos) {
        InsumoEntity existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setStock(datos.getStock());
        existente.setUnidadMedida(datos.getUnidadMedida());
        existente.setFechaVencimiento(datos.getFechaVencimiento());
        existente.setProveedor(datos.getProveedor());
        return insumoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        insumoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        InsumoEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        insumoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        InsumoEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        insumoRepository.save(entidad);
    }
}
