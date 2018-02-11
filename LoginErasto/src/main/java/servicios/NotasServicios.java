package servicios;

import dao.NotasDAO;
import model.Nota;
/**
 *
 * @author Erasto Rojas Sánchez
 */
public class NotasServicios{
 
    public Nota getNotas(long idAlumno,long idAsignatura){
        
        NotasDAO dao = new NotasDAO();
        return dao.getNotas(idAlumno,idAsignatura);
    }

    public void updateNota(Nota NotaNueva){
        
        NotasDAO dao = new NotasDAO();
        dao.updateNota(NotaNueva);
    }

    public void delNota(Nota NotaNueva){
        
        NotasDAO dao = new NotasDAO();
        dao.delNota(NotaNueva);
    }
}
