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
import pe.com.restaurantearuba.entity.Comprobante;
import pe.com.restaurantearuba.service.ComprobanteService;

@RestController
@RequestMapping("/api/comprobantes")
@RequiredArgsConstructor
public class ComprobanteRestController {

    private final ComprobanteService comprobanteService;

    @GetMapping
    public List<Comprobante> listar(@RequestParam(required = false) String buscar) {
        return comprobanteService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public Comprobante obtener(@PathVariable Integer id) {
        return comprobanteService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Comprobante> registrar(@Valid @RequestBody Comprobante comprobante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comprobanteService.registrar(comprobante));
    }

    @PutMapping("/{id}")
    public Comprobante actualizar(@PathVariable Integer id, @Valid @RequestBody Comprobante comprobante) {
        return comprobanteService.actualizar(id, comprobante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        comprobanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        comprobanteService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        comprobanteService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
