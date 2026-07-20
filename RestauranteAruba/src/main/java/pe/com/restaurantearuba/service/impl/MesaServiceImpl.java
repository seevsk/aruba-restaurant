package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.MesaEntity;
import pe.com.restaurantearuba.repository.MesaRepository;
import pe.com.restaurantearuba.service.MesaService;

@Service
@RequiredArgsConstructor
@Transactional
public class MesaServiceImpl implements MesaService {

    private static final String DISPONIBLE = "DISPONIBLE";
    private static final String INACTIVO = "INACTIVO";

    private final MesaRepository mesaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MesaEntity> listar() {
        return mesaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MesaEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return mesaRepository.findByNumeroContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public MesaEntity obtenerPorId(Integer id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada: " + id));
    }

    @Override
    public MesaEntity registrar(MesaEntity mesa) {
        mesa.setId(null);
        if (mesa.getEstado() == null || mesa.getEstado().isBlank()) {
            mesa.setEstado(DISPONIBLE);
        }
        return mesaRepository.save(mesa);
    }

    @Override
    public MesaEntity actualizar(Integer id, MesaEntity datos) {
        MesaEntity existente = obtenerPorId(id);
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
        MesaEntity entidad = obtenerPorId(id);
        entidad.setEstado(DISPONIBLE);
        mesaRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        MesaEntity entidad = obtenerPorId(id);
        entidad.setEstado(INACTIVO);
        mesaRepository.save(entidad);
    }
}
