/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ApiKey;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 *
 * @author Eduardo DAW
 */
public class ApiKeyDAO{
    /*
    public List<ApiKey> selectApiKeyJDBC(ApiKey k) {
        List<ApiKey> lista = new ArrayList<>();
        DBConnection db = new DBConnection();
        Connection con = null;
        ApiKey ak = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM APIKEY WHERE APIKEY = ?");
 
            stmt.setString(1, k.getAPIKEY());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String APIKEY = rs.getString("APIKEY");
                int NUM_PETICIONES = rs.getInt("NUM_PETICIONES");
                Date FECHA_ULTIMA_PETICION = rs.getDate("FECHA_ULTIMA_PETICION");
                
                ak = new ApiKey();
                ak.setID(ID);
                ak.setAPIKEY(APIKEY);
                ak.setNUM_PETICIONES(NUM_PETICIONES);
                ak.setFECHA_ULTIMA_PETICION(FECHA_ULTIMA_PETICION);
                lista.add(ak);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ApiKeyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return lista;
    } 
    */
    public ApiKey selectApiKeyJDBC(ApiKey k) {
        DBConnection db = new DBConnection();
        Connection con = null;
        ApiKey ak = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<ApiKey> h = new BeanHandler<>(ApiKey.class);
            ak = qr.query(con, "SELECT * FROM APIKEY "
                             + "WHERE APIKEY = ?", h, k.getAPIKEY());
        } catch (Exception e) {
            Logger.getLogger(ApiKeyDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
             db.cerrarConexion(con);
        }
        return ak;
    }
    
}
