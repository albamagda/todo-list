package todoList.To_do_List.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
