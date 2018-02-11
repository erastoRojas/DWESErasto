package model;
/**
 *
 * @author Erasto Rojas Sánchez
 */
public class Asignatura {
    
    private long id;
    private String nombre;
    private String curso;
    private String ciclo;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Asignatura() {
    }
}
