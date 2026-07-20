package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.DistritoEntity;
import pe.com.restaurantearuba.repository.DistritoRepository;
import pe.com.restaurantearuba.service.DistritoService;

@Service
@RequiredArgsConstructor
@Transactional
public class DistritoServiceImpl implements DistritoService {

    private final DistritoRepository distritoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DistritoEntity> listar() {
        return distritoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DistritoEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return distritoRepository.findByEstadoTrue();
        }
        return distritoRepository.findByEstadoTrueAndNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public DistritoEntity obtenerPorId(Integer id) {
        return distritoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Distrito no encontrado: " + id));
    }

    @Override
    public DistritoEntity registrar(DistritoEntity distrito) {
        distrito.setId(null);
        if (distrito.getEstado() == null) {
            distrito.setEstado(true);
        }
        return distritoRepository.save(distrito);
    }

    @Override
    public DistritoEntity actualizar(Integer id, DistritoEntity datos) {
        DistritoEntity existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        return distritoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        distritoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        DistritoEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        distritoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        DistritoEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        distritoRepository.save(entidad);
    }
}
