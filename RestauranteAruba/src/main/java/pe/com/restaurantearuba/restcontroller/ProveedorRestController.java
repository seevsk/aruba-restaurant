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
import pe.com.restaurantearuba.entity.ProveedorEntity;
import pe.com.restaurantearuba.service.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorRestController {

    private final ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorEntity> listar(@RequestParam(required = false) String buscar) {
        return proveedorService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public ProveedorEntity obtener(@PathVariable Integer id) {
        return proveedorService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProveedorEntity> registrar(@Valid @RequestBody ProveedorEntity proveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.registrar(proveedor));
    }

    @PutMapping("/{id}")
    public ProveedorEntity actualizar(@PathVariable Integer id, @Valid @RequestBody ProveedorEntity proveedor) {
        return proveedorService.actualizar(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        proveedorService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        proveedorService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
