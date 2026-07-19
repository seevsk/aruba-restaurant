package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.Pedido;
import pe.com.restaurantearuba.repository.PedidoRepository;
import pe.com.restaurantearuba.service.PedidoService;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private static final String PENDIENTE = "PENDIENTE";
    private static final String CANCELADO = "CANCELADO";

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return pedidoRepository.buscar(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido obtenerPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: " + id));
    }

    @Override
    public Pedido registrar(Pedido pedido) {
        pedido.setId(null);
        if (pedido.getEstado() == null || pedido.getEstado().isBlank()) {
            pedido.setEstado(PENDIENTE);
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido actualizar(Integer id, Pedido datos) {
        Pedido existente = obtenerPorId(id);
        existente.setEmpleado(datos.getEmpleado());
        existente.setMesa(datos.getMesa());
        existente.setCliente(datos.getCliente());
        return pedidoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        Pedido entidad = obtenerPorId(id);
        entidad.setEstado(PENDIENTE);
        pedidoRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        Pedido entidad = obtenerPorId(id);
        entidad.setEstado(CANCELADO);
        pedidoRepository.save(entidad);
    }
}
