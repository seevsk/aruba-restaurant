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
import pe.com.restaurantearuba.entity.ProductoEntity;
import pe.com.restaurantearuba.service.CategoriaService;
import pe.com.restaurantearuba.service.ProductoService;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("productos", productoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "producto/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new ProductoEntity());
        cargarCombos(model);
        return "producto/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.obtenerPorId(id));
        cargarCombos(model);
        return "producto/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("producto") ProductoEntity producto,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            cargarCombos(model);
            return "producto/form";
        }
        boolean esNuevo = producto.getId() == null;
        if (esNuevo) {
            productoService.registrar(producto);
        } else {
            productoService.actualizar(producto.getId(), producto);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Producto registrado correctamente." : "Producto actualizado correctamente.");
        return "redirect:/productos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/productos";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        productoService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto habilitado.");
        return "redirect:/productos";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        productoService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto deshabilitado.");
        return "redirect:/productos";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
    }
}
