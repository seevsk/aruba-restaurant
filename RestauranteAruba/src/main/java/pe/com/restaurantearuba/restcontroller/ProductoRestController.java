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
import pe.com.restaurantearuba.entity.ProductoEntity;
import pe.com.restaurantearuba.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoRestController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoEntity> listar(@RequestParam(required = false) String buscar) {
        return productoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public ProductoEntity obtener(@PathVariable Integer id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProductoEntity> registrar(@Valid @RequestBody ProductoEntity producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.registrar(producto));
    }

    @PutMapping("/{id}")
    public ProductoEntity actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoEntity producto) {
        return productoService.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        productoService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        productoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
