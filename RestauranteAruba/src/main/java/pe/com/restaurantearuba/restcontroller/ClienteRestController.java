package pe.com.restaurantearuba.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.ClienteEntity;
import pe.com.restaurantearuba.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteRestController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteEntity> listar(@RequestParam(required = false) String buscar) {
        return clienteService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public ClienteEntity obtener(@PathVariable Integer id) {
        return clienteService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> registrar(@Valid @RequestBody ClienteEntity cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.registrar(cliente));
    }

    @PutMapping("/{id}")
    public ClienteEntity actualizar(@PathVariable Integer id, @Valid @RequestBody ClienteEntity cliente) {
        return clienteService.actualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        clienteService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        clienteService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
