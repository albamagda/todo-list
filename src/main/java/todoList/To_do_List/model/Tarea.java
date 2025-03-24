package todoList.To_do_List.model;

public class Tarea{
    private String nombre;
    private String contenido;
    private String comentarios;
    private boolean acabada;
}

public Tarea(String nombre, String contenido, String comentarios, boolean acabada){
    this.nombre = nombre;
    this.contenido = contenido;
    this.comentarios = comentarios;
    this.acabada = acabada;
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

public Boolean isAcabada() {
    return acabada;
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
public void setAcabada(boolean acabada) {
    this.acabada = acabada;
}