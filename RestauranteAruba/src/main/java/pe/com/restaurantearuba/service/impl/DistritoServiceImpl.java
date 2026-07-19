package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.Distrito;
import pe.com.restaurantearuba.repository.DistritoRepository;
import pe.com.restaurantearuba.service.DistritoService;

@Service
@RequiredArgsConstructor
@Transactional
public class DistritoServiceImpl implements DistritoService {

    private final DistritoRepository distritoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Distrito> listar() {
        return distritoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Distrito> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return distritoRepository.findByNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Distrito obtenerPorId(Integer id) {
        return distritoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Distrito no encontrado: " + id));
    }

    @Override
    public Distrito registrar(Distrito distrito) {
        distrito.setId(null);
        if (distrito.getEstado() == null) {
            distrito.setEstado(true);
        }
        return distritoRepository.save(distrito);
    }

    @Override
    public Distrito actualizar(Integer id, Distrito datos) {
        Distrito existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        return distritoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        distritoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        Distrito entidad = obtenerPorId(id);
        entidad.setEstado(true);
        distritoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        Distrito entidad = obtenerPorId(id);
        entidad.setEstado(false);
        distritoRepository.save(entidad);
    }
}
