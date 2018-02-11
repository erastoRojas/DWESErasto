package servicios;

import dao.AsignaturasDAO;
import java.util.List;
import model.Asignatura;

/**
 *
 * @author Erasto Rojas Sánchez
 */
public class AsignaturasServicios {
    
    public List<Asignatura> mostrarAsignaturas(){
        AsignaturasDAO dao = new AsignaturasDAO();
        
        return dao.getAllAsignaturasDBUils();
    }
     public void addAsignatura(Asignatura AsignaturaNuevo){

        AsignaturasDAO dao = new AsignaturasDAO();
        dao.insertAsignaturasDBUtils(AsignaturaNuevo);
    }
    public void updateAsignatura(Asignatura AsignaturaNuevo){

        AsignaturasDAO dao = new AsignaturasDAO();
        dao.updateAsignaturasDBUtils(AsignaturaNuevo);
    }
    public void deleteAsignatura(Asignatura AsignaturaNuevo){

        AsignaturasDAO dao = new AsignaturasDAO();
        dao.deleteAsignaturasDBUtils(AsignaturaNuevo);
    }
}
