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
import pe.com.restaurantearuba.entity.DetallePedido;
import pe.com.restaurantearuba.service.DetallePedidoService;
import pe.com.restaurantearuba.service.PedidoService;
import pe.com.restaurantearuba.service.ProductoService;

@Controller
@RequestMapping("/detalles-pedido")
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("detalles", detallePedidoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "detalle-pedido/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("detalle", new DetallePedido());
        cargarCombos(model);
        return "detalle-pedido/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("detalle", detallePedidoService.obtenerPorId(id));
        cargarCombos(model);
        return "detalle-pedido/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("detalle") DetallePedido detalle,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            cargarCombos(model);
            return "detalle-pedido/form";
        }
        boolean esNuevo = detalle.getId() == null;
        if (esNuevo) {
            detallePedidoService.registrar(detalle);
        } else {
            detallePedidoService.actualizar(detalle.getId(), detalle);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Detalle registrado correctamente." : "Detalle actualizado correctamente.");
        return "redirect:/detalles-pedido";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            detallePedidoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Detalle eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/detalles-pedido";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("productos", productoService.listar());
    }
}
