package servicios;

import com.google.api.client.json.GenericJson;
import dao.AlumnosDAO;
import java.io.IOException;
import java.util.List;
import model.Alumno;

/**
 *
 * @author Erasto Rojas Sánchez
 */
public class AlumnosServicios {
    
    public List<Alumno> getAllAlumnos() throws IOException{
        AlumnosDAO dao = new AlumnosDAO();
        return dao.getAllAlumnosJDBC();
    }
    public int addAlumno(Alumno alumnoNuevo) throws IOException{

        AlumnosDAO dao = new AlumnosDAO();
        return dao.insertAlumnoJDBC(alumnoNuevo);
    }
    public int updateAlumno(Alumno alumnoNuevo){

        AlumnosDAO dao = new AlumnosDAO();
        return dao.updateAlumnoJDBC(alumnoNuevo);
    }
    public int deleteAlumno(Alumno alumnoNuevo){

        AlumnosDAO dao = new AlumnosDAO();
        return dao.deleteAlumnoJDBC(alumnoNuevo);
    }
}
