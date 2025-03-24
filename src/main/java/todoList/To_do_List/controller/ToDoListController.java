package todoList.To_do_List.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        model.addAttribute("todolist", tareaService.obtenerTarea());
        model.addAttribute("message", mensaje);
        return "to-do-list";
    }

    @GetMapping("/eliminar/{nombre}")
    public String mostrarConfirmacion(@PathVariable("nombre") String nombre, Model model) {
        Tarea tarea = tareaService.obtenerTareaPorNombre(nombre); // Método para obtener la tarea
        model.addAttribute("tarea", tarea);
        return "deleteNote"; // Nombre del HTML de eliminación
    }

    @GetMapping("/eliminar-confirmado/{nombre}")
    public String eliminarTarea(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes) {
        boolean eliminado =tareaService.eliminarTarea(nombre); // Método para eliminar la tarea
        if(eliminado){
            redirectAttributes.addAttribute("message", "Tarea eliminada con éxito");
        }else{
            redirectAttributes.addFlashAttribute("message", "No se pudo eliminar la tarea");
        }
        return "redirect:/to-do-list";
    }
}
