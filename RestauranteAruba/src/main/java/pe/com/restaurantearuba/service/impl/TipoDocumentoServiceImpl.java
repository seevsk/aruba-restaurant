package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.TipoDocumentoEntity;
import pe.com.restaurantearuba.repository.TipoDocumentoRepository;
import pe.com.restaurantearuba.service.TipoDocumentoService;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumentoEntity> listar() {
        return tipoDocumentoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumentoEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return tipoDocumentoRepository.findByEstadoTrue();
        }
        return tipoDocumentoRepository.findByEstadoTrueAndNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoDocumentoEntity obtenerPorId(Integer id) {
        return tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado: " + id));
    }

    @Override
    public TipoDocumentoEntity registrar(TipoDocumentoEntity tipoDocumento) {
        tipoDocumento.setId(null);
        if (tipoDocumento.getEstado() == null) {
            tipoDocumento.setEstado(true);
        }
        return tipoDocumentoRepository.save(tipoDocumento);
    }

    @Override
    public TipoDocumentoEntity actualizar(Integer id, TipoDocumentoEntity datos) {
        TipoDocumentoEntity existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        return tipoDocumentoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        tipoDocumentoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        TipoDocumentoEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        tipoDocumentoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        TipoDocumentoEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        tipoDocumentoRepository.save(entidad);
    }
}
