package dao;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Alumno;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author erasto
 */
public class UsersDAO {
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource (DataSource dataSource){
        
        this.dataSource = dataSource;
    }
    
    public List<User> getAllUserssJDBC() {
        JdbcTemplate jtm = new JdbcTemplate(
          DBConnection.getInstance().getDataSource());
        List<User> users = jtm.query("Select * from USERS",
        new BeanPropertyRowMapper(User.class));
        
        return users;
    }
    
    public User addUserJDBCTemplate(User a) {
       
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
          DBConnection.getInstance().getDataSource()).withTableName("USERS").usingGeneratedKeyColumns("ID");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("NOMBRE", a.getNombre());
        parameters.put("PASSWORD", a.getPassword());
        parameters.put("ACTIVO", a.getActivo());
        parameters.put("CODIGO_ACTIVACION", a.getCodigo_activacion());
        parameters.put("FECHA_ACTIVACION", a.getFecha_activacion());
        parameters.put("EMAIL", a.getEmail()); 
        a.setId((int) jdbcInsert.executeAndReturnKey(parameters).longValue());
        
        return a;
    }

    public int updateActivoJDBCTemplate(String codigo) {
        int fila;
        try{
            String SQL = "update USERS set ACTIVO = 1 where CODIGO_ACTIVACION = ?";
            JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
            fila = jtm.update(SQL,codigo);
        }catch(DataAccessException e){
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, e);
            fila = 0;
        }    
            
        return fila;
    }
    
    public User selectUserJDBCTemplate(String nombre) {
        User user;
        try{
            String SQL = "select * from USERS where NOMBRE = ?";
            JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
            user = (User) jtm.queryForObject(
                SQL, new Object[] {nombre},
                new BeanPropertyRowMapper(User.class));  
        }catch(DataAccessException e){
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, e);
            user = null;
        }
        
        return user;
    }
    
    public User selectCodeJDBCTemplate(String codigo) {
        User user;
        try{
            String SQL = "select * from USERS where CODIGO_ACTIVACION = ?";
            JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
            user = (User)jtm.queryForObject(
                SQL, new Object[] {codigo},
                new BeanPropertyRowMapper(User.class));
        }catch(DataAccessException e){
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, e);
            user = null;
        }
        return user;
    }


}
