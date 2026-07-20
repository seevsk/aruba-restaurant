package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.CategoriaEntity;
import pe.com.restaurantearuba.repository.CategoriaRepository;
import pe.com.restaurantearuba.service.CategoriaService;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaEntity> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return categoriaRepository.findByEstadoTrue();
        }
        return categoriaRepository.findByEstadoTrueAndNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaEntity obtenerPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada: " + id));
    }

    @Override
    public CategoriaEntity registrar(CategoriaEntity categoria) {
        categoria.setId(null);
        if (categoria.getEstado() == null) {
            categoria.setEstado(true);
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaEntity actualizar(Integer id, CategoriaEntity datos) {
        CategoriaEntity existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        return categoriaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        categoriaRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        CategoriaEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        categoriaRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        CategoriaEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        categoriaRepository.save(entidad);
    }
}
