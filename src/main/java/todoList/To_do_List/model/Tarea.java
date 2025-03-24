package todoList.To_do_List.model;

public class Tarea{
    private String nombre;
    private String contenido;
    private String comentarios;
}

public Tarea(String nombre, String contenido, String comentarios){
    this.nombre = nombre;
    this.contenido = contenido;
    this.comentarios = comentarios;
}

public Tarea(){}

public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getContenido() {
    return contenido;
}
public void setContenido(String contenido) {
    this.contenido = contenido;
}

public String getComentarios() {
    return comentarios;
}
public void setComentarios(String comentarios) {
    this.comentarios = comentarios;
}