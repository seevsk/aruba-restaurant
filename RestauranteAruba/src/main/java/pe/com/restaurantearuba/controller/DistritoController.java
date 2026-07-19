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
import pe.com.restaurantearuba.entity.Distrito;
import pe.com.restaurantearuba.service.DistritoService;

@Controller
@RequestMapping("/distritos")
@RequiredArgsConstructor
public class DistritoController {

    private final DistritoService distritoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("distritos", distritoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "distrito/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("distrito", new Distrito());
        return "distrito/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("distrito", distritoService.obtenerPorId(id));
        return "distrito/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("distrito") Distrito distrito,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "distrito/form";
        }
        boolean esNuevo = distrito.getId() == null;
        if (esNuevo) {
            distritoService.registrar(distrito);
        } else {
            distritoService.actualizar(distrito.getId(), distrito);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Distrito registrado correctamente." : "Distrito actualizado correctamente.");
        return "redirect:/distritos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            distritoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Distrito eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/distritos";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        distritoService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Distrito habilitado.");
        return "redirect:/distritos";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        distritoService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Distrito deshabilitado.");
        return "redirect:/distritos";
    }
}
