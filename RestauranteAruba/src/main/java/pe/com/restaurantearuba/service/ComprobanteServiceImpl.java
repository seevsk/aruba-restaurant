package pe.com.restaurantearuba.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.Comprobante;
import pe.com.restaurantearuba.repository.ComprobanteRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comprobante> listar() {
        return comprobanteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comprobante> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return comprobanteRepository.findByTipoContainingIgnoreCaseOrSerieContainingIgnoreCaseOrNumeroContainingIgnoreCase(
                texto, texto, texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Comprobante obtenerPorId(Integer id) {
        return comprobanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado: " + id));
    }

    @Override
    public Comprobante registrar(Comprobante comprobante) {
        comprobante.setId(null);
        if (comprobante.getEstado() == null) {
            comprobante.setEstado(true);
        }
        return comprobanteRepository.save(comprobante);
    }

    @Override
    public Comprobante actualizar(Integer id, Comprobante datos) {
        Comprobante existente = obtenerPorId(id);
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
        Comprobante entidad = obtenerPorId(id);
        entidad.setEstado(true);
        comprobanteRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        Comprobante entidad = obtenerPorId(id);
        entidad.setEstado(false);
        comprobanteRepository.save(entidad);
    }
}
