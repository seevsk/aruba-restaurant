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
import pe.com.restaurantearuba.entity.ComprobanteEntity;
import pe.com.restaurantearuba.service.ComprobanteService;
import pe.com.restaurantearuba.service.MetodoPagoService;
import pe.com.restaurantearuba.service.PedidoService;

@Controller
@RequestMapping("/comprobantes")
@RequiredArgsConstructor
public class ComprobanteController {

    private final ComprobanteService comprobanteService;
    private final PedidoService pedidoService;
    private final MetodoPagoService metodoPagoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("comprobantes", comprobanteService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "comprobante/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("comprobante", new ComprobanteEntity());
        cargarCombos(model);
        return "comprobante/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("comprobante", comprobanteService.obtenerPorId(id));
        cargarCombos(model);
        return "comprobante/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("comprobante") ComprobanteEntity comprobante,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            cargarCombos(model);
            return "comprobante/form";
        }
        boolean esNuevo = comprobante.getId() == null;
        if (esNuevo) {
            comprobanteService.registrar(comprobante);
        } else {
            comprobanteService.actualizar(comprobante.getId(), comprobante);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Comprobante registrado correctamente." : "Comprobante actualizado correctamente.");
        return "redirect:/comprobantes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            comprobanteService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Comprobante eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/comprobantes";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        comprobanteService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Comprobante habilitado.");
        return "redirect:/comprobantes";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        comprobanteService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Comprobante deshabilitado.");
        return "redirect:/comprobantes";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("metodosPago", metodoPagoService.listar());
    }
}
