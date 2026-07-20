package pe.com.restaurantearuba.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pe.com.restaurantearuba.entity.TipoDocumentoEntity;
import pe.com.restaurantearuba.service.TipoDocumentoService;

@Controller
@RequestMapping("/tipos-documento")
@RequiredArgsConstructor
public class TipoDocumentoController {

    private final TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("tiposDocumento", tipoDocumentoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "tipo-documento/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipoDocumento", new TipoDocumentoEntity());
        return "tipo-documento/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("tipoDocumento", tipoDocumentoService.obtenerPorId(id));
        return "tipo-documento/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("tipoDocumento") TipoDocumentoEntity tipoDocumento,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tipo-documento/form";
        }
        boolean esNuevo = tipoDocumento.getId() == null;
        if (esNuevo) {
            tipoDocumentoService.registrar(tipoDocumento);
        } else {
            tipoDocumentoService.actualizar(tipoDocumento.getId(), tipoDocumento);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Tipo de documento registrado correctamente." : "Tipo de documento actualizado correctamente.");
        return "redirect:/tipos-documento";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            tipoDocumentoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Tipo de documento eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/tipos-documento";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        tipoDocumentoService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Tipo de documento habilitado.");
        return "redirect:/tipos-documento";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        tipoDocumentoService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Tipo de documento deshabilitado.");
        return "redirect:/tipos-documento";
    }
}
