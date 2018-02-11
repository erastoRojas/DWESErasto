/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Movimiento;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


/**
 *
 * @author erasto
 */
public class MovimientosDAO {

    public List<Movimiento> getAllMovimientos() {
        List <Movimiento> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;

        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Movimiento>> h = new BeanListHandler<>(Movimiento.class);

            lista = qr.query(con,"select * FROM movimientos", h);

        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            db.cerrarConexion(con);
        }
        return lista;
    }
    
    public List<Movimiento> getMovimientosDAO(Movimiento mo, Date date1, Date date2){
        List<Movimiento> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Movimiento>> h = new BeanListHandler<>(Movimiento.class);
            lista = qr.query(con, "SELECT * FROM MOVIMIENTOS WHERE mo_ncu = ? AND mo_fec BETWEEN ? AND ?", h,mo.getMo_ncu(),date1,date2);

        } catch (Exception ex){
            Logger.getLogger(MovimientosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return lista;
    }

    public Movimiento getCuentaDAO(Movimiento mo){
        Movimiento cuenta = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Movimiento> h = new BeanHandler<>(Movimiento.class);
            cuenta = qr.query(con, "SELECT * FROM MOVIMIENTOS WHERE mo_ncu = ?", h,mo.getMo_ncu());

        } catch (Exception ex){
            Logger.getLogger(MovimientosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cuenta;
    }
}
