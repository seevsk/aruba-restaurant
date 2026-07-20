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
import pe.com.restaurantearuba.service.ClienteService;
import pe.com.restaurantearuba.service.DistritoService;
import pe.com.restaurantearuba.service.TipoDocumentoService;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final DistritoService distritoService;
    private final TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("clientes", clienteService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "cliente/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new ClienteEntity());
        cargarCombos(model);
        return "cliente/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteService.obtenerPorId(id));
        cargarCombos(model);
        return "cliente/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cliente") ClienteEntity cliente,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            cargarCombos(model);
            return "cliente/form";
        }
        boolean esNuevo = cliente.getId() == null;
        if (esNuevo) {
            clienteService.registrar(cliente);
        } else {
            clienteService.actualizar(cliente.getId(), cliente);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Cliente registrado correctamente." : "Cliente actualizado correctamente.");
        return "redirect:/clientes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            clienteService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/clientes";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        clienteService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente habilitado.");
        return "redirect:/clientes";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        clienteService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente deshabilitado.");
        return "redirect:/clientes";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("distritos", distritoService.listar());
        model.addAttribute("tiposDocumento", tipoDocumentoService.listar());
    }
}
