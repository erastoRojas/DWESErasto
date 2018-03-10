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
import model.Cuenta;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 *
 * @author erasto
 */
public class CuentasDAO
{

    public Cuenta getCuentaDAO(Cuenta cu) {//api
        Cuenta cuenta = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Cuenta> h = new BeanHandler<>(Cuenta.class);
            cuenta = qr.query(con, "SELECT * FROM CUENTAS "
                                 + "WHERE cu_ncu = ?", h,cu.getCu_ncu());

        } catch (Exception ex){
            Logger.getLogger(CuentasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cuenta;
    }
    
    public Cuenta getSaldoDAO(Cuenta cu){//api
        Cuenta cuenta = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Cuenta> h = new BeanHandler<>(Cuenta.class);
            cuenta = qr.query(con, "SELECT * FROM CUENTAS "
                                 + "WHERE cu_sal = ?", h,cu.getCu_sal());

        } catch (Exception ex){
            Logger.getLogger(CuentasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cuenta;
    }
    
    public int updateCuentaDAO(Cuenta cu){//api
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE CUENTAS SET cu_sal = ? "
                                                        + "WHERE cu_ncu = ?");
 
            stmt.setInt(1, cu.getCu_sal());
            stmt.setString(2, cu.getCu_ncu());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CuentasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }

    public int crearCuentaDAO(Cuenta cu) {
    
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO CUENTAS (cu_ncu,cu_dn1,cu_dn2,cu_sal) "
                                                        + "VALUES(?,?,?,?)");                                       
            stmt.setString(1, cu.getCu_ncu());
            stmt.setString(2, cu.getCu_dn1());
            stmt.setString(3, cu.getCu_dn2());
            stmt.setInt(4, cu.getCu_sal());
            
            filas = stmt.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(MovimientosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;  
    }
    
}
