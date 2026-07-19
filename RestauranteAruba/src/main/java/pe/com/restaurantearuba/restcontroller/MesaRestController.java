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
import pe.com.restaurantearuba.entity.Mesa;
import pe.com.restaurantearuba.service.MesaService;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaRestController {

    private final MesaService mesaService;

    @GetMapping
    public List<Mesa> listar(@RequestParam(required = false) String buscar) {
        return mesaService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public Mesa obtener(@PathVariable Integer id) {
        return mesaService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Mesa> registrar(@Valid @RequestBody Mesa mesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.registrar(mesa));
    }

    @PutMapping("/{id}")
    public Mesa actualizar(@PathVariable Integer id, @Valid @RequestBody Mesa mesa) {
        return mesaService.actualizar(id, mesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        mesaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        mesaService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        mesaService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
