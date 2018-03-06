/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Trabajador;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 *
 * @author erasto
 */
public class TrabajadoresDAO {

    public Trabajador getTrabajadorDAO(Trabajador tr) {
        Trabajador trabajador = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Trabajador> h = new BeanHandler<>(Trabajador.class);
            trabajador = qr.query(con, "SELECT * FROM TRABAJADORES "
                                     + "WHERE tr_id = ?", h,tr.getTr_id());

        } catch (Exception ex){
            Logger.getLogger(TrabajadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return trabajador;
    }

    public int crearTrabajadorDAO(Trabajador tr) {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO TRABAJADORES (tr_id,tr_no) "
                                                        + "VALUES(?,?)");                                       
            stmt.setString(1, tr.getTr_id());
            stmt.setString(2, tr.getTr_no());
            
            filas = stmt.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(MovimientosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;  
    }
    
}
