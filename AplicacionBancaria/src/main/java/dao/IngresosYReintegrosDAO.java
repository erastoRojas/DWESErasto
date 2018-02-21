/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
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
public class IngresosYReintegrosDAO
{

    public Cuenta getCuentaDAO(Cuenta cu) {
        Cuenta cuenta = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Cuenta> h = new BeanHandler<>(Cuenta.class);
            cuenta = qr.query(con, "SELECT * FROM CUENTAS WHERE cu_ncu = ?", h,cu.getCu_ncu());

        } catch (Exception ex){
            Logger.getLogger(IngresosYReintegrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cuenta;
    }
    
}
