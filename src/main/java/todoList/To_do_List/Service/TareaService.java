package todoList.To_do_List.service;

import java.util.HashMap;
import java.util.Map;
import todoList.To_do_List.model.Tarea;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class TareaService {
    private Map<String, Tarea> todoList = new HashMap<>();

    //Prueba para crear tarea y verla en el html
    @PostConstruct
    public void init(){
        Tarea tarea = new Tarea("Prueba", "Prueba", "comentarios", false);
        Tarea tarea2 = new Tarea("Prueba2", "Pruebaasd", "comentarasdios", false);
        todoList.put(tarea.getNombre(), tarea);
        todoList.put(tarea2.getNombre(), tarea2);
    }

    public Map<String, Tarea> obtenerTarea(){
        return todoList;
    }

    public Tarea obtenerTareaPorNombre(String nombre){
        return todoList.get(nombre);
    }

    public void aniadirTarea (Tarea tarea){
        todoList.put(tarea.getNombre(), tarea);
    }

    public boolean eliminarTarea (String nombre){
        Tarea tarea = todoList.get(nombre);
        boolean flag = false;
        if(tarea != null ){
            todoList.remove(nombre);
            flag = true;
        }
        return flag;
    }

    public void changeTareaHecha(String nombre){
        Tarea tarea = todoList.get(nombre);
        tarea.setAcabada(!tarea.isAcabada());
    }

    public void actualizarContacto(String nombreOriginal, Tarea tareaActualizada){
        if(!nombreOriginal.equals(tareaActualizada.getNombre())){
            todoList.remove(nombreOriginal);
        }
        todoList.put(tareaActualizada.getNombre(), tareaActualizada);
    }
}
