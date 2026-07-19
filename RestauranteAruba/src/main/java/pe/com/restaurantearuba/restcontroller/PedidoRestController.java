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
import pe.com.restaurantearuba.entity.Pedido;
import pe.com.restaurantearuba.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoRestController {

    private final PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listar(@RequestParam(required = false) String buscar) {
        return pedidoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public Pedido obtener(@PathVariable Integer id) {
        return pedidoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> registrar(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.registrar(pedido));
    }

    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable Integer id, @Valid @RequestBody Pedido pedido) {
        return pedidoService.actualizar(id, pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        pedidoService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        pedidoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
