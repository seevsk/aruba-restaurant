package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.MetodoPagoEntity;
import pe.com.restaurantearuba.repository.MetodoPagoRepository;
import pe.com.restaurantearuba.service.MetodoPagoService;

@Service
@RequiredArgsConstructor
@Transactional
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MetodoPagoEntity> listar() {
        return metodoPagoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetodoPagoEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return metodoPagoRepository.findByNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public MetodoPagoEntity obtenerPorId(Integer id) {
        return metodoPagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Metodo de pago no encontrado: " + id));
    }

    @Override
    public MetodoPagoEntity registrar(MetodoPagoEntity metodoPago) {
        metodoPago.setId(null);
        if (metodoPago.getEstado() == null) {
            metodoPago.setEstado(true);
        }
        return metodoPagoRepository.save(metodoPago);
    }

    @Override
    public MetodoPagoEntity actualizar(Integer id, MetodoPagoEntity datos) {
        MetodoPagoEntity existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        return metodoPagoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        metodoPagoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        MetodoPagoEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        metodoPagoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        MetodoPagoEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        metodoPagoRepository.save(entidad);
    }
}
