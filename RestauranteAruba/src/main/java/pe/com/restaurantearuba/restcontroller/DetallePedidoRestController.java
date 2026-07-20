package pe.com.restaurantearuba.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.DetallePedidoEntity;
import pe.com.restaurantearuba.service.DetallePedidoService;

@RestController
@RequestMapping("/api/detalles-pedido")
@RequiredArgsConstructor
public class DetallePedidoRestController {

    private final DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedidoEntity> listar(@RequestParam(required = false) String buscar) {
        return detallePedidoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public DetallePedidoEntity obtener(@PathVariable Integer id) {
        return detallePedidoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<DetallePedidoEntity> registrar(@Valid @RequestBody DetallePedidoEntity detallePedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detallePedidoService.registrar(detallePedido));
    }

    @PutMapping("/{id}")
    public DetallePedidoEntity actualizar(@PathVariable Integer id, @Valid @RequestBody DetallePedidoEntity detallePedido) {
        return detallePedidoService.actualizar(id, detallePedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detallePedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
