package pe.com.restaurantearuba.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.Producto;
import pe.com.restaurantearuba.repository.ProductoRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return productoRepository.findByNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + id));
    }

    @Override
    public Producto registrar(Producto producto) {
        producto.setId(null);
        if (producto.getEstado() == null) {
            producto.setEstado(true);
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto datos) {
        Producto existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());
        existente.setCategoria(datos.getCategoria());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        productoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        Producto entidad = obtenerPorId(id);
        entidad.setEstado(true);
        productoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        Producto entidad = obtenerPorId(id);
        entidad.setEstado(false);
        productoRepository.save(entidad);
    }
}
