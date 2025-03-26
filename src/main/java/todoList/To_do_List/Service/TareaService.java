package todoList.To_do_List.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todoList.To_do_List.model.Tarea;
import todoList.To_do_List.repository.TareaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TareaService {
    private final TareaRepository tareaRepositorio;

    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepositorio.findAll();
    }
    

    public Optional<Tarea> findByNombre(String nombre) {
        return tareaRepositorio.findByNombre(nombre);
    }

    public Tarea guardarTarea(Tarea tarea) {
        if (tareaRepositorio.existsByNombre(tarea.getNombre())) {
            throw new RuntimeException("La tarea con este nombre ya existe");
        }
        return tareaRepositorio.save(tarea);
    }

    public void eliminarTarea(String nombre) {
        if (!tareaRepositorio.existsById(nombre)) {
            throw new RuntimeException("La tarea no existe");
        }
        tareaRepositorio.deleteById(nombre);
    }

    public Tarea actualizarTarea(String nombreOriginal, Tarea tareaActualizada) {
        Tarea tarea = tareaRepositorio.findByNombre(nombreOriginal)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        if (!nombreOriginal.equals(tareaActualizada.getNombre()) && tareaRepositorio.existsByNombre(tareaActualizada.getNombre())) {
            throw new RuntimeException("El nuevo nombre ya está en uso");
        }

        tarea.setNombre(tareaActualizada.getNombre());
        tarea.setContenido(tareaActualizada.getContenido());
        tarea.setComentarios(tareaActualizada.getComentarios());
        tarea.setAcabada(tareaActualizada.isAcabada());

        return tareaRepositorio.save(tarea);
    }

    public Tarea cambiarEstadoTarea(String nombre) {
        Tarea tarea = tareaRepositorio.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        
        tarea.setAcabada(!tarea.isAcabada());
        return tareaRepositorio.save(tarea);
    }
}