package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.Categoria;
import pe.com.restaurantearuba.repository.CategoriaRepository;
import pe.com.restaurantearuba.service.CategoriaService;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return categoriaRepository.findByNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria obtenerPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada: " + id));
    }

    @Override
    public Categoria registrar(Categoria categoria) {
        categoria.setId(null);
        if (categoria.getEstado() == null) {
            categoria.setEstado(true);
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Integer id, Categoria datos) {
        Categoria existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        return categoriaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        categoriaRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        Categoria entidad = obtenerPorId(id);
        entidad.setEstado(true);
        categoriaRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        Categoria entidad = obtenerPorId(id);
        entidad.setEstado(false);
        categoriaRepository.save(entidad);
    }
}
