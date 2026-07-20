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
import pe.com.restaurantearuba.entity.EmpleadoEntity;
import pe.com.restaurantearuba.service.DistritoService;
import pe.com.restaurantearuba.service.EmpleadoService;
import pe.com.restaurantearuba.service.TipoDocumentoService;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final DistritoService distritoService;
    private final TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        model.addAttribute("empleados", empleadoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        return "empleado/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("empleado", new EmpleadoEntity());
        cargarCombos(model);
        return "empleado/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("empleado", empleadoService.obtenerPorId(id));
        cargarCombos(model);
        return "empleado/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("empleado") EmpleadoEntity empleado,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            cargarCombos(model);
            return "empleado/form";
        }
        boolean esNuevo = empleado.getId() == null;
        if (esNuevo) {
            empleadoService.registrar(empleado);
        } else {
            empleadoService.actualizar(empleado.getId(), empleado);
        }
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Empleado registrado correctamente." : "Empleado actualizado correctamente.");
        return "redirect:/empleados";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            empleadoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Empleado eliminado.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: tiene registros relacionados.");
        }
        return "redirect:/empleados";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        empleadoService.habilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Empleado habilitado.");
        return "redirect:/empleados";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        empleadoService.deshabilitar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Empleado deshabilitado.");
        return "redirect:/empleados";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("distritos", distritoService.listar());
        model.addAttribute("tiposDocumento", tipoDocumentoService.listar());
    }
}
