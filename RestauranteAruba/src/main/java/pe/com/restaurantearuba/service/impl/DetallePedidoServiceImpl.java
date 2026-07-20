package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.DetallePedidoEntity;
import pe.com.restaurantearuba.repository.DetallePedidoRepository;
import pe.com.restaurantearuba.service.DetallePedidoService;

@Service
@RequiredArgsConstructor
@Transactional
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoEntity> listar() {
        return detallePedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return detallePedidoRepository.buscar(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public DetallePedidoEntity obtenerPorId(Integer id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de pedido no encontrado: " + id));
    }

    @Override
    public DetallePedidoEntity registrar(DetallePedidoEntity detallePedido) {
        detallePedido.setId(null);
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public DetallePedidoEntity actualizar(Integer id, DetallePedidoEntity datos) {
        DetallePedidoEntity existente = obtenerPorId(id);
        existente.setPedido(datos.getPedido());
        existente.setProducto(datos.getProducto());
        existente.setCantidad(datos.getCantidad());
        existente.setPrecioUnitario(datos.getPrecioUnitario());
        existente.setNota(datos.getNota());
        return detallePedidoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        detallePedidoRepository.delete(obtenerPorId(id));
    }
}
