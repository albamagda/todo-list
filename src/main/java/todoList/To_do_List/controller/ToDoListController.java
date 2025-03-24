package com.todoList.To_do_List.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import com.todoList.To_do_List.model.Tarea;

import com.agenda.service.ContactoService;

@Controller
@RequestMapping("/todolist")
public class AgendaController {
    private final TareaService tareaService;

    public ToDoListController(TareaService tareaService){
        this.tareaService = tareaService;
    }

    @GetMapping("")
    public String mostrarTarea(Model model, @ModelAttribute("message") String mensaje){
        model.addAttribute("tarea", tareaService.obtenerTarea());
        model.addAttribute("message", mensaje);
        return "todolist";
    }

    @GetMapping("/nuevo")
    public String nuevaTareaForm(Model model){
        model.addAttribute("tarea", new Tarea());
        return "nuevaTarea";
    }

    @PostMapping("/aniadir")
    public String aniadirTarea(@ModelAttribute Tarea tarea, RedirectAttributes redirectAttributes){
        tareaService.aniadirTarea(tarea);
        redirectAttributes.addFlashAttribute("aniadir", "Tarea añadida con éxito");
        return "redirect:/todolist";
    }

    @PostMapping("/eliminar/{nombre}")
    public String eliminarTarea(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        boolean eliminado = tareaService.eliminarTarea(nombre);
        if(eliminado){
            redirectAttributes.addAttribute("message", "Tarea eliminada con éxito");
        }else{
            redirectAttributes.addFlashAttribute("message", "No se pudo eliminar una tarea");
        }
        return "redirect:/todolist";
    }

    @GetMapping("/editar/{nombre}")
    public String editarTarea(@PathVariable("nombre") String nombre, Model model){
        Tarea tarea = tareaService.obtenerTareaPorNombre(nombre);
        model.addAttribute("tarea", tarea);
        return "editarTarea";
    }

    @PostMapping("/actualizar/{nombre}")
    public String actualizarTarea(@PathVariable("nombre") String nombreOriginal, Tarea tareaActualizada, RedirectAttributes redirectAttributes){
        tareaService.actualizarTarea(nombreOriginal, tareaActualizada);
        redirectAttributes.addFlashAttribute("message", "Tarea actualizada con éxito");
        return "redirect:/todolist";
    }

    @PostMapping("/acabada/{nombre}")
    public String cambiarAcabada(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        contactoService.marcarTareaHecha(nombre);
        redirectAttributes.addFlashAttribute("message", "Estado de tarea acabada actualizado");
        return "redirect:/todolist";
    }
}