package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Nota;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 *
 * @author Erasto Rojas Sánchez
 */
public class NotasDAO{
 
    public Nota getNotas(long idAlumno,long idAsignatura){
        Nota lista = null;

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            BeanHandler<Nota> h = new BeanHandler<>(Nota.class);
            lista = qr.query(con, "SELECT * FROM NOTAS WHERE ID_ALUMNO=? AND ID_ASIGNATURA=?", h,idAlumno,idAsignatura);

        } catch (Exception ex){
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;
    }
    
    public void updateNota(Nota n){

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            int filas = qr.update(con,"UPDATE NOTAS SET NOTA=? WHERE ID_ALUMNO=? AND ID_ASIGNATURA=?",n.getNota(),n.getIdAlumno(),n.getIdAsignatura());
            if(filas == 0){
                try
                {
                    QueryRunner qr2 = new QueryRunner();
                    ResultSetHandler<Nota> h = new BeanHandler<>(Nota.class);
                    Nota id = qr2.insert(con,"INSERT INTO NOTAS (ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES(?,?,?)",h,n.getIdAlumno(),n.getIdAsignatura(),n.getNota());

                } catch (SQLException ex)
                {
                    Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            con.commit();
        } catch (Exception ex){
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
    }
    public void delNota(Nota n){

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr= new QueryRunner();
            qr.update(con,"DELETE FROM NOTAS WHERE ID_ALUMNO=? AND ID_ASIGNATURA=?",n.getIdAlumno(),n.getIdAsignatura());
        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
    }
}
