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
import pe.com.restaurantearuba.entity.Insumo;
import pe.com.restaurantearuba.service.InsumoService;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
public class InsumoRestController {

    private final InsumoService insumoService;

    @GetMapping
    public List<Insumo> listar(@RequestParam(required = false) String buscar) {
        return insumoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public Insumo obtener(@PathVariable Integer id) {
        return insumoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Insumo> registrar(@Valid @RequestBody Insumo insumo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(insumoService.registrar(insumo));
    }

    @PutMapping("/{id}")
    public Insumo actualizar(@PathVariable Integer id, @Valid @RequestBody Insumo insumo) {
        return insumoService.actualizar(id, insumo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        insumoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        insumoService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        insumoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
