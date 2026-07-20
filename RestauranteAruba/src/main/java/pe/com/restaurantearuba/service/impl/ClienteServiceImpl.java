package pe.com.restaurantearuba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.ClienteEntity;
import pe.com.restaurantearuba.repository.ClienteRepository;
import pe.com.restaurantearuba.service.ClienteService;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteEntity> listar() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteEntity> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        return clienteRepository.buscar(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteEntity obtenerPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + id));
    }

    @Override
    public ClienteEntity registrar(ClienteEntity cliente) {
        cliente.setId(null);
        if (cliente.getEstado() == null) {
            cliente.setEstado(true);
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteEntity actualizar(Integer id, ClienteEntity datos) {
        ClienteEntity existente = obtenerPorId(id);
        existente.setNumeroDocumento(datos.getNumeroDocumento());
        existente.setNombres(datos.getNombres());
        existente.setApellidoPaterno(datos.getApellidoPaterno());
        existente.setApellidoMaterno(datos.getApellidoMaterno());
        existente.setTelefono(datos.getTelefono());
        existente.setDireccion(datos.getDireccion());
        existente.setDistrito(datos.getDistrito());
        existente.setTipoDocumento(datos.getTipoDocumento());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        clienteRepository.delete(obtenerPorId(id));
    }

    @Override
    public void habilitar(Integer id) {
        ClienteEntity entidad = obtenerPorId(id);
        entidad.setEstado(true);
        clienteRepository.save(entidad);
    }

    @Override
    public void deshabilitar(Integer id) {
        ClienteEntity entidad = obtenerPorId(id);
        entidad.setEstado(false);
        clienteRepository.save(entidad);
    }
}
