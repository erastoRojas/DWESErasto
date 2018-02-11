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

    public int updateNota(Nota NotaNueva){
        
        NotasDAO dao = new NotasDAO();
        return dao.updateNota(NotaNueva);
    }

    public int delNota(Nota NotaNueva){
        
        NotasDAO dao = new NotasDAO();
        return dao.delNota(NotaNueva);
    }
}
