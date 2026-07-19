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
import pe.com.restaurantearuba.entity.Categoria;
import pe.com.restaurantearuba.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("categorias", categoriaService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "categoria/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("categoria", categoriaService.obtenerPorId(id));
        return "categoria/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("categoria") Categoria categoria,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "categoria/form";
        }
        boolean esNuevo = categoria.getId() == null;
        if (esNuevo) {
            categoriaService.registrar(categoria);
        } else {
            categoriaService.actualizar(categoria.getId(), categoria);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Categoria registrada correctamente." : "Categoria actualizada correctamente.");
        return "redirect:/categorias";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoria eliminada.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/categorias";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        categoriaService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Categoria habilitada.");
        return "redirect:/categorias";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        categoriaService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Categoria deshabilitada.");
        return "redirect:/categorias";
    }
}
