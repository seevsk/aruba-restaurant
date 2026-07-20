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
import pe.com.restaurantearuba.entity.TipoDocumentoEntity;
import pe.com.restaurantearuba.service.TipoDocumentoService;

@RestController
@RequestMapping("/api/tipos-documento")
@RequiredArgsConstructor
public class TipoDocumentoRestController {

    private final TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public List<TipoDocumentoEntity> listar(@RequestParam(required = false) String buscar) {
        return tipoDocumentoService.buscar(buscar);
    }

    @GetMapping("/{id}")
    public TipoDocumentoEntity obtener(@PathVariable Integer id) {
        return tipoDocumentoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<TipoDocumentoEntity> registrar(@Valid @RequestBody TipoDocumentoEntity tipoDocumento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoDocumentoService.registrar(tipoDocumento));
    }

    @PutMapping("/{id}")
    public TipoDocumentoEntity actualizar(@PathVariable Integer id, @Valid @RequestBody TipoDocumentoEntity tipoDocumento) {
        return tipoDocumentoService.actualizar(id, tipoDocumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoDocumentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Void> habilitar(@PathVariable Integer id) {
        tipoDocumentoService.habilitar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitar(@PathVariable Integer id) {
        tipoDocumentoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
