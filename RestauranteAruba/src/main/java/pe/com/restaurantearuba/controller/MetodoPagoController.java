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
import pe.com.restaurantearuba.entity.MetodoPagoEntity;
import pe.com.restaurantearuba.service.MetodoPagoService;

@Controller
@RequestMapping("/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("metodosPago", metodoPagoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "metodo-pago/list";
    }

    @GetMapping("/habilitar")
    public String habilitarPagina(Model model) {
        model.addAttribute("metodosPago", metodoPagoService.listar());
        return "metodo-pago/habilitar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("metodoPago", new MetodoPagoEntity());
        return "metodo-pago/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("metodoPago", metodoPagoService.obtenerPorId(id));
        return "metodo-pago/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("metodoPago") MetodoPagoEntity metodoPago,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "metodo-pago/form";
        }
        boolean esNuevo = metodoPago.getId() == null;
        if (esNuevo) {
            metodoPagoService.registrar(metodoPago);
        } else {
            metodoPagoService.actualizar(metodoPago.getId(), metodoPago);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Metodo de pago registrado correctamente." : "Metodo de pago actualizado correctamente.");
        return "redirect:/metodos-pago";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            metodoPagoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Metodo de pago eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/metodos-pago";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        metodoPagoService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Metodo de pago habilitado.");
        return "redirect:/metodos-pago/habilitar";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        metodoPagoService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Metodo de pago deshabilitado.");
        return "redirect:/metodos-pago/habilitar";
    }
}
