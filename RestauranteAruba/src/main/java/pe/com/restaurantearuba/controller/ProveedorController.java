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
import pe.com.restaurantearuba.entity.ProveedorEntity;
import pe.com.restaurantearuba.service.ProveedorService;

@Controller
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("proveedores", proveedorService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "proveedor/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new ProveedorEntity());
        return "proveedor/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("proveedor", proveedorService.obtenerPorId(id));
        return "proveedor/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("proveedor") ProveedorEntity proveedor,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "proveedor/form";
        }
        boolean esNuevo = proveedor.getId() == null;
        if (esNuevo) {
            proveedorService.registrar(proveedor);
        } else {
            proveedorService.actualizar(proveedor.getId(), proveedor);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Proveedor registrado correctamente." : "Proveedor actualizado correctamente.");
        return "redirect:/proveedores";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            proveedorService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Proveedor eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/proveedores";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        proveedorService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Proveedor habilitado.");
        return "redirect:/proveedores";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        proveedorService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Proveedor deshabilitado.");
        return "redirect:/proveedores";
    }
}
