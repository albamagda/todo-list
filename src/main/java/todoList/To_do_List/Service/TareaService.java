package todoList.To_do_List.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todoList.To_do_List.model.Tarea;
import todoList.To_do_List.repositorios.TareaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TareaService {
    private final TareaRepositorio tareaRepositorio;

    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepositorio.findAll();
    }

    public Optional<Tarea> obtenerTareaPorNombre(String nombre) {
        return tareaRepositorio.findByNombre(nombre);
    }

    public Tarea guardarTarea(Tarea tarea) {
        if (tareaRepositorio.existsByNombre(tarea.getNombre())) {
            throw new RuntimeException("La tarea con este nombre ya existe");
        }
        return tareaRepositorio.save(tarea);
    }

    public void eliminarTarea(String nombre) {
        if (!tareaRepositorio.existsByNombre(nombre)) {
            throw new RuntimeException("La tarea no existe");
        }
        tareaRepositorio.deleteByNombre(nombre);
    }

    public Tarea actualizarTarea(String nombreOriginal, Tarea tareaActualizada) {
        Tarea tarea = tareaRepositorio.findByNombre(nombreOriginal)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        if (!nombreOriginal.equals(tareaActualizada.getNombre()) && tareaRepositorio.existsByNombre(tareaActualizada.getNombre())) {
            throw new RuntimeException("El nuevo nombre ya está en uso");
        }

        tarea.setNombre(tareaActualizada.getNombre());
        tarea.setDescripcion(tareaActualizada.getDescripcion());
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