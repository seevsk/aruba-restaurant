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
import pe.com.restaurantearuba.entity.MesaEntity;
import pe.com.restaurantearuba.service.MesaService;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaRestController {

    private final MesaService mesaService;

    @GetMapping
    public List<MesaEntity> listar(@RequestParam(required = false) String buscar) {
        return mesaService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public MesaEntity obtener(@PathVariable Integer id) {
        return mesaService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<MesaEntity> registrar(@Valid @RequestBody MesaEntity mesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.registrar(mesa));
    }

    @PutMapping("/{id}")
    public MesaEntity actualizar(@PathVariable Integer id, @Valid @RequestBody MesaEntity mesa) {
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
