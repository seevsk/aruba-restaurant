package pe.com.restaurantearuba.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.Mesa;
import pe.com.restaurantearuba.repository.MesaRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MesaServiceImpl implements MesaService {

    private static final String DISPONIBLE = "DISPONIBLE";
    private static final String INACTIVO = "INACTIVO";

    private final MesaRepository mesaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Mesa> listar() {
        return mesaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mesa> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return mesaRepository.findByNumeroContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mesa obtenerPorId(Integer id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada: " + id));
    }

    @Override
    public Mesa registrar(Mesa mesa) {
        mesa.setId(null);
        if (mesa.getEstado() == null || mesa.getEstado().isBlank()) {
            mesa.setEstado(DISPONIBLE);
        }
        return mesaRepository.save(mesa);
    }

    @Override
    public Mesa actualizar(Integer id, Mesa datos) {
        Mesa existente = obtenerPorId(id);
        existente.setNumero(datos.getNumero());
        existente.setCapacidad(datos.getCapacidad());
        return mesaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        mesaRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        Mesa entidad = obtenerPorId(id);
        entidad.setEstado(DISPONIBLE);
        mesaRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        Mesa entidad = obtenerPorId(id);
        entidad.setEstado(INACTIVO);
        mesaRepository.save(entidad);
    }
}
