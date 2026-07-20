package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.ComprobanteEntity;
import pe.com.restaurantearuba.repository.ComprobanteRepository;
import pe.com.restaurantearuba.service.ComprobanteService;

@Service
@RequiredArgsConstructor
@Transactional
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ComprobanteEntity> listar() {
        return comprobanteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprobanteEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return comprobanteRepository.findByEstadoTrue();
        }
        return comprobanteRepository.buscarHabilitados(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public ComprobanteEntity obtenerPorId(Integer id) {
        return comprobanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado: " + id));
    }

    @Override
    public ComprobanteEntity registrar(ComprobanteEntity comprobante) {
        comprobante.setId(null);
        if (comprobante.getEstado() == null) {
            comprobante.setEstado(true);
        }
        return comprobanteRepository.save(comprobante);
    }

    @Override
    public ComprobanteEntity actualizar(Integer id, ComprobanteEntity datos) {
        ComprobanteEntity existente = obtenerPorId(id);
        existente.setTipo(datos.getTipo());
        existente.setSerie(datos.getSerie());
        existente.setNumero(datos.getNumero());
        existente.setTotal(datos.getTotal());
        existente.setPedido(datos.getPedido());
        existente.setMetodoPago(datos.getMetodoPago());
        return comprobanteRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        comprobanteRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        ComprobanteEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        comprobanteRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        ComprobanteEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        comprobanteRepository.save(entidad);
    }
}
