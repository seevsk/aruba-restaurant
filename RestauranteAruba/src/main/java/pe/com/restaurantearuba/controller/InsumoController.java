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
import pe.com.restaurantearuba.entity.InsumoEntity;
import pe.com.restaurantearuba.service.InsumoService;
import pe.com.restaurantearuba.service.ProveedorService;

@Controller
@RequestMapping("/insumos")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService insumoService;
    private final ProveedorService proveedorService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("insumos", insumoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "insumo/list";
    }

    @GetMapping("/habilitar")
    public String habilitarPagina(Model model) {
        model.addAttribute("insumos", insumoService.listar());
        return "insumo/habilitar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("insumo", new InsumoEntity());
        cargarCombos(model);
        return "insumo/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("insumo", insumoService.obtenerPorId(id));
        cargarCombos(model);
        return "insumo/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("insumo") InsumoEntity insumo,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            cargarCombos(model);
            return "insumo/form";
        }
        boolean esNuevo = insumo.getId() == null;
        if (esNuevo) {
            insumoService.registrar(insumo);
        } else {
            insumoService.actualizar(insumo.getId(), insumo);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Insumo registrado correctamente." : "Insumo actualizado correctamente.");
        return "redirect:/insumos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            insumoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Insumo eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/insumos";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        insumoService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Insumo habilitado.");
        return "redirect:/insumos/habilitar";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        insumoService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Insumo deshabilitado.");
        return "redirect:/insumos/habilitar";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("proveedores", proveedorService.listar());
    }
}
