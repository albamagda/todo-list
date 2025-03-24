package todoList.To_do_List;

import java.util.HashMap;
import java.util.Map;
import todoList.To_do_List.model.Tarea;
import org.springframework.stereotype.Service;

@Service
public class TareaService {
    private Map<String, Tarea> todoList = new HashMap<>();

    public Map<String, Tarea> obtenerTarea(){
        return todoList;
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

    public void actualizarTarea(String nombreOriginal, Tarea tareaActualizada){
        if(!nombreOriginal.equals(tareaActualizada.getNombre())){
            todoList.remove(nombreOriginal);
        }
        todoList.put(tareaActualizada.getNombre(), tareaActualizada);
    }

    public Tarea obtenerTareaPorNombre(String nombre){
        return todoList.get(nombre);
    }

    public void marcarTareaHecha(String nombre){
        Tarea tarea = todoList.get(nombre);
        if(tarea != null){
            tarea.setAcabada(!tarea.isAcabada());
        }
    }
}
