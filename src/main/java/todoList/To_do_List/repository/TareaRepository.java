package todoList.To_do_List.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todoList.To_do_List.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long>{
    Optional<Tarea> findByNombre(String nombre);
    
    boolean existsByNombre(String nombre);

    void deleteByNombre(String nombre);
}
