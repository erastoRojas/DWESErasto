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
     public Asignatura addAsignatura(Asignatura AsignaturaNuevo){

        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.insertAsignaturasDBUtils(AsignaturaNuevo);
    }
    public int updateAsignatura(Asignatura AsignaturaNuevo){

        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.updateAsignaturasDBUtils(AsignaturaNuevo);
    }
    public int deleteAsignatura(Asignatura AsignaturaNuevo){

        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.deleteAsignaturasDBUtils(AsignaturaNuevo);
    }
}
