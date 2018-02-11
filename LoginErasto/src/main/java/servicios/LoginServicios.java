/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsersDAO;
import java.util.List;
import model.User;

/**
 *
 * @author erasto
 */
public class LoginServicios {
    
    public List<User> getAllUsers(){
        UsersDAO dao = new UsersDAO();
        return dao.getAllUserssJDBC();
    }
    public User addUser(User UserNuevo){
        UsersDAO dao = new UsersDAO();
        return dao.addUserJDBCTemplate(UserNuevo);
    }
    public void updateActivo(String codigo){
        UsersDAO dao = new UsersDAO();
        dao.updateActivoJDBCTemplate(codigo);
    }
    
    public User selectUser(String nombre){
        UsersDAO dao = new UsersDAO();
        return dao.selectUserJDBCTemplate(nombre);
    }
    
    public User selectCode(String codigo){
        UsersDAO dao = new UsersDAO();
        return dao.selectCodeJDBCTemplate(codigo);
    }
}