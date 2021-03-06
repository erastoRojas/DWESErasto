/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alumno;
import model.Asignatura;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
/**
 *
 * @author oscar
 */
public class AsignaturasDAO {

    public List<Asignatura> getAllAsignaturasDBUils() {
        List <Asignatura> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;

        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Asignatura>> h = new BeanListHandler<Asignatura>(Asignatura.class);

            lista = qr.query(con,"select * FROM ASIGNATURAS", h);

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            db.cerrarConexion(con);
        }
        return lista;

    }
    
    public Asignatura insertAsignaturasDBUtils(Asignatura a){
        DBConnection db = new DBConnection();
        Connection con = null;
        Asignatura id = new Asignatura();
        try{
            con = db.getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Asignatura> h = new BeanHandler<>(Asignatura.class);
            
            id = qr.insert(con,"INSERT INTO ASIGNATURAS(ID,NOMBRE,CURSO,CICLO) "
                             + "VALUES (?,?,?,?)",h,
            a.getId(),a.getNombre(),a.getCurso(),a.getCiclo()) ;
            a.setId(id.getId());
            con.commit();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            db.cerrarConexion(con);
        }
        return id;
    }
    
    public int updateAsignaturasDBUtils(Asignatura a){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,"UPDATE ASIGNATURAS SET NOMBRE=?,CURSO=?,CICLO=? "
                                + "WHERE ID=?",
            a.getNombre(),a.getCurso(),a.getCiclo(),a.getId());
                    

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int deleteAsignaturasDBUtils(Asignatura a){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try{
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            
            filas = qr.update(con,"DELETE FROM ASIGNATURAS "
                                + "WHERE ID =?" , a.getId());
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
}