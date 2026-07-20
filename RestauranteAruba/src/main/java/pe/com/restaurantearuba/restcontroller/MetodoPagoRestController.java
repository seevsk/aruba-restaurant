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
import pe.com.restaurantearuba.entity.MetodoPagoEntity;
import pe.com.restaurantearuba.service.MetodoPagoService;

@RestController
@RequestMapping("/api/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoRestController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping
    public List<MetodoPagoEntity> listar(@RequestParam(required = false) String buscar) {
        return metodoPagoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public MetodoPagoEntity obtener(@PathVariable Integer id) {
        return metodoPagoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<MetodoPagoEntity> registrar(@Valid @RequestBody MetodoPagoEntity metodoPago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metodoPagoService.registrar(metodoPago));
    }

    @PutMapping("/{id}")
    public MetodoPagoEntity actualizar(@PathVariable Integer id, @Valid @RequestBody MetodoPagoEntity metodoPago) {
        return metodoPagoService.actualizar(id, metodoPago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        metodoPagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        metodoPagoService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        metodoPagoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
