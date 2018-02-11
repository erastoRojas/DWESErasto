/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
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
    
}
