/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author erasto
 */
public class ClientesDAO {
    
    public List<Cliente> getAllClientesDBUils() {
        List <Cliente> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;

        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Cliente>> h = new BeanListHandler<Cliente>(Cliente.class);

            lista = qr.query(con,"select * FROM clientes", h);

        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            db.cerrarConexion(con);
        }
        return lista;

    }
    
    public Cliente getClienteDAO(Cliente cl) {
        Cliente cliente = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Cliente> h = new BeanHandler<>(Cliente.class);
            cliente = qr.query(con, "SELECT * FROM CLIENTES "
                                  + "WHERE cl_dni = ?", h,cl.getCl_dni());

        } catch (Exception ex){
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cliente;
    }
    

    public int updateClienteDAO(Cliente cl){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE CLIENTES SET cl_sal = ? "
                                                        + "WHERE cl_dni = ?");
 
            stmt.setInt(1, cl.getCl_sal());
            stmt.setString(2, cl.getCl_dni());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
}
