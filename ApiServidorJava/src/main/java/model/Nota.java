package model;

/**
 *
 * @author Erasto Rojas Sánchez
 */
public class Nota {
    private long idAlumno;
    private long idAsignatura;
    private int nota;

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
