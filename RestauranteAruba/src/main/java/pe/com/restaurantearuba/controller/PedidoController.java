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
import pe.com.restaurantearuba.entity.ClienteEntity;
import pe.com.restaurantearuba.entity.PedidoEntity;
import pe.com.restaurantearuba.service.ClienteService;
import pe.com.restaurantearuba.service.EmpleadoService;
import pe.com.restaurantearuba.service.MesaService;
import pe.com.restaurantearuba.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final EmpleadoService empleadoService;
    private final MesaService mesaService;
    private final ClienteService clienteService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("pedidos", pedidoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "pedido/list";
    }

    @GetMapping("/habilitar")
    public String habilitarPagina(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedido/habilitar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pedido", new PedidoEntity());
        cargarCombos(model);
        return "pedido/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("pedido", pedidoService.obtenerPorId(id));
        cargarCombos(model);
        return "pedido/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("pedido") PedidoEntity pedido,
                           BindingResult bindingResult, Model model,
                           @RequestParam(required = false) Integer clienteId,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            cargarCombos(model);
            return "pedido/form";
        }
        pedido.setCliente(clienteId != null ? ClienteEntity.builder().id(clienteId).build() : null);
        boolean esNuevo = pedido.getId() == null;
        if (esNuevo) {
            pedidoService.registrar(pedido);
        } else {
            pedidoService.actualizar(pedido.getId(), pedido);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Pedido registrado correctamente." : "Pedido actualizado correctamente.");
        return "redirect:/pedidos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            pedidoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Pedido eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/pedidos";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        pedidoService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Pedido reabierto como pendiente.");
        return "redirect:/pedidos/habilitar";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        pedidoService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Pedido cancelado.");
        return "redirect:/pedidos/habilitar";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("empleados", empleadoService.listar());
        model.addAttribute("mesas", mesaService.listar());
        model.addAttribute("clientes", clienteService.listar());
    }
}
