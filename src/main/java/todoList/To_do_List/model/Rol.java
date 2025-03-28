package todoList.To_do_List.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    public Rol(String nombre){
        this.nombre = nombre;
    }

}
