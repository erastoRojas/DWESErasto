package servicios;

import dao.AsignaturasDAO;
import java.io.IOException;
import java.util.List;
import model.Asignatura;

/**
 *
 * @author Erasto Rojas Sánchez
 */
public class AsignaturasServicios {
    
    public List<Asignatura> mostrarAsignaturas() throws IOException{
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.getAllAsignaturasDBUils();
    }
     public String addAsignatura(Asignatura AsignaturaNuevo) throws IOException{
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.insertAsignaturasDBUtils(AsignaturaNuevo);
    }
    public String updateAsignatura(Asignatura AsignaturaNuevo) throws IOException{
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.updateAsignaturasDBUtils(AsignaturaNuevo);
    }
    public String deleteAsignatura(Asignatura AsignaturaNuevo) throws IOException{
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.deleteAsignaturasDBUtils(AsignaturaNuevo);
    }
}
