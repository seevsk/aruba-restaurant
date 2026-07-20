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
import pe.com.restaurantearuba.entity.EmpleadoEntity;
import pe.com.restaurantearuba.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoRestController {

    private final EmpleadoService empleadoService;

    @GetMapping
    public List<EmpleadoEntity> listar(@RequestParam(required = false) String buscar) {
        return empleadoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public EmpleadoEntity obtener(@PathVariable Integer id) {
        return empleadoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<EmpleadoEntity> registrar(@Valid @RequestBody EmpleadoEntity empleado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.registrar(empleado));
    }

    @PutMapping("/{id}")
    public EmpleadoEntity actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoEntity empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        empleadoService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        empleadoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
