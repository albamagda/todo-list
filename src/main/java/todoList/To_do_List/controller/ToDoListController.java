package todoList.To_do_List.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import todoList.To_do_List.model.Tarea;
import todoList.To_do_List.service.TareaService;



@Controller
@RequestMapping("/to-do-list")
public class ToDoListController {
    private final TareaService tareaService;

    public ToDoListController(TareaService tareaService){
        this.tareaService = tareaService;
    }

    @GetMapping("")
    public String mostrarToDoList(Model model, @ModelAttribute("message") String mensaje){
        model.addAttribute("todolist", tareaService.obtenerTodasLasTareas());
        model.addAttribute("message", mensaje);
        return "to-do-list";
    }

    @GetMapping("/crear")
    public String crearTareaForm(Model model){
        model.addAttribute("tarea", new Tarea());
        return "addNote";
    }

    //Endpoint POST de crear tarea
    @PostMapping("/crear")
    public String crearTarea(@ModelAttribute Tarea tarea, RedirectAttributes redirectAttributes){
        tareaService.guardarTarea(tarea);
        redirectAttributes.addFlashAttribute("guardar", "Tarea añadida con exito");
        return "redirect:/to-do-list";
    }

    @GetMapping("editar/{nombre}")
    public String editarTarea(@PathVariable("nombre") String nombre, Model model){
        Optional<Tarea> tarea = tareaService.findByNombre(nombre);
        model.addAttribute("tarea", tarea);
        return "editNote";
    }

    @PostMapping("/actualizar/{nombre}")
    public String actualizarTarea(@PathVariable("nombre") String nombreOriginal, Tarea tareaActualizada, RedirectAttributes redirectAttributes){
        tareaService.actualizarTarea(nombreOriginal, tareaActualizada);
        redirectAttributes.addFlashAttribute("message", "Contacto actualizado");
        return "redirect:/to-do-list";
    }


    @GetMapping("/eliminar/{nombre}")
    public String mostrarConfirmacion(@PathVariable("nombre") String nombre, Model model) {
        Optional<Tarea> tarea = tareaService.findByNombre(nombre); // Método para obtener la tarea
        model.addAttribute("tarea", tarea);
        return "deleteNote"; // Nombre del HTML de eliminación
    }

    @GetMapping("/eliminar-confirmado/{nombre}")
    public String eliminarTarea(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes) {
        tareaService.eliminarTarea(nombre); // Método para eliminar la tarea
        return "redirect:/to-do-list";
    }

    @GetMapping("/actualizar-completada/{nombre}")
    public String actualizarCompletadaTarea(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        tareaService.cambiarEstadoTarea(nombre);
        redirectAttributes.addFlashAttribute("message", "Estado de tarea actualizado");
        return "redirect:/to-do-list";
    }

    @GetMapping("/check/{nombre}")
    @ResponseBody
    public boolean checkTarea(@PathVariable String nombre){
        return tareaService.obtenerTodasLasTareas().contains(tareaService.findByNombre(nombre).get());
    }
}
