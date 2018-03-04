package dao;

import model.Alumno;
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

/**
 *
 * @author Erasto Rojas Sánchez
 */
public class AlumnosDAO {

     public List<Alumno> getAllAlumnosJDBC() {
        List<Alumno> lista = new ArrayList<>();
        Alumno nuevo = null;
        
        DBConnection db = new DBConnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql;
            sql = "SELECT * FROM alumnos";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fn = rs.getDate("fecha_nacimiento");
                Boolean mayor = rs.getBoolean("mayor_edad");
                nuevo = new Alumno();
                nuevo.setFecha_nacimiento(fn);
                nuevo.setId(id);
                nuevo.setMayor_edad(mayor);
                nuevo.setNombre(nombre);
                lista.add(nuevo);
            }
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.cerrarConexion(con);
        }
        return lista;
    }

    public int insertAlumnoJDBC(Alumno a) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("INSERT INTO ALUMNOS (NOMBRE,FECHA_NACIMIENTO,MAYOR_EDAD) "
                                      + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());   
            filas = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                if (stmt != null) {
                    stmt.close();
                }
            }catch (SQLException ex) {
                    Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.cerrarConexion(con);
        }
        return filas;
    }
    public int updateAlumnoJDBC(Alumno a) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("UPDATE ALUMNOS SET NOMBRE = ?,FECHA_NACIMIENTO = ?, MAYOR_EDAD = ? "
                                      + "WHERE ID = ?");
 
            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());
            stmt.setLong(4,a.getId());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                if (stmt != null) {
                    stmt.close();
                }
            }catch (SQLException ex) {
                    Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int deleteAlumnoJDBC(Alumno a) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("DELETE FROM ALUMNOS "
                                      + "WHERE ID =" + a.getId());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            filas = -1;
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return filas;
    }
}

//HIKARI
/*
 public int deleteUserByIdJDBC(long key) {

        int filasErased = -1;
        Connection connection = null;

        PreparedStatement stmt = null;
        try {
            connection = DBConnection.getInstance().getConnection();;

            stmt = connection.prepareStatement(SqlQuery.DELETE_ALUMNO, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, key);

            filasErased = stmt.executeUpdate();

        } catch (Exception e) {

            if (e.getMessage().contains(ConstantesError.errorForeingkey)) {
                filasErased = ConstantesError.CodeErrorClaveForanea;
            }
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            DBConnection.getInstance().cerrarConexion(connection);
        }
        return filasErased;
    }*/

//DELETE DE MIGUEL CON ROLLBACK Y DOBLE DELETE
/*
public int delUser2(Alumno a){
        Connection con = null;
        int filas = 0;
        try {
            
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            String sql = "DELETE FROM notas WHERE ID_ALUMNO = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getId());
            
            filas += stmt.executeUpdate();
            
            sql = "DELETE FROM alumnos WHERE ID = ?";
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getId());

            filas += stmt.executeUpdate();
            con.commit();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (con!=null)
                    con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return filas;
    }*/