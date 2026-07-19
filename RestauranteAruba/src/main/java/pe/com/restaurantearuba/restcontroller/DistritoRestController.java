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
import pe.com.restaurantearuba.entity.Distrito;
import pe.com.restaurantearuba.service.DistritoService;

@RestController
@RequestMapping("/api/distritos")
@RequiredArgsConstructor
public class DistritoRestController {

    private final DistritoService distritoService;

    @GetMapping
    public List<Distrito> listar(@RequestParam(required = false) String buscar) {
        return distritoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public Distrito obtener(@PathVariable Integer id) {
        return distritoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Distrito> registrar(@Valid @RequestBody Distrito distrito) {
        return ResponseEntity.status(HttpStatus.CREATED).body(distritoService.registrar(distrito));
    }

    @PutMapping("/{id}")
    public Distrito actualizar(@PathVariable Integer id, @Valid @RequestBody Distrito distrito) {
        return distritoService.actualizar(id, distrito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        distritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        distritoService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        distritoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
