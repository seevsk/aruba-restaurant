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
import pe.com.restaurantearuba.entity.MesaEntity;
import pe.com.restaurantearuba.service.MesaService;

@Controller
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("mesas", mesaService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "mesa/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("mesa", new MesaEntity());
        return "mesa/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("mesa", mesaService.obtenerPorId(id));
        return "mesa/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("mesa") MesaEntity mesa,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "mesa/form";
        }
        boolean esNuevo = mesa.getId() == null;
        if (esNuevo) {
            mesaService.registrar(mesa);
        } else {
            mesaService.actualizar(mesa.getId(), mesa);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Mesa registrada correctamente." : "Mesa actualizada correctamente.");
        return "redirect:/mesas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            mesaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Mesa eliminada.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/mesas";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        mesaService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Mesa habilitada.");
        return "redirect:/mesas";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        mesaService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Mesa deshabilitada.");
        return "redirect:/mesas";
    }
}
