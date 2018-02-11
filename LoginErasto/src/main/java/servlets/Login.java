/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import config.Configuration;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import servicios.LoginServicios;
import servicios.MandarMail;
import utils.Constantes;
import utils.GenerarCodigo;
import utils.PasswordHash;

/**
 *
 * @author erasto
// */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String op = request.getParameter("op");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String cod_act = request.getParameter("cod_act");

        LoginServicios ud = new LoginServicios();
        User user;
        
        if (op != null){    
            switch (op) {  
                    
                case "REGISTRAR":

                    int activo = 0;  
                    User u = new User();

                    user = ud.selectUser(nombre);
                    
                    if(user == null) {
                        try {                                                            
                        String hash = PasswordHash.getInstance().createHash(pwd);
                        u.setPassword(hash);
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String cod = GenerarCodigo.randomAlphaNumeric(20);
                        LocalDateTime fecha_activacion = LocalDateTime.now();
                        
                        u.setNombre(nombre);
                        u.setEmail(email);
                        u.setFecha_activacion(fecha_activacion);
                        u.setActivo(activo);
                        u.setCodigo_activacion(cod);
                        ud.addUser(u);
                        
                        MandarMail mail = new MandarMail();
                        mail.mandarMail("erasto.sanchez.44@gmail.com", "Codigo de activación: http://localhost:8080/basedatos1Erasto/login?op=ACTIVAR&cod_act="+cod, "holaw");
                        
                        request.setAttribute("mensaje", Constantes.mensajeRegistroSinValidar);
                    }else{
                        if(user.getNombre().equals(nombre)){
                            request.setAttribute("mensaje", Constantes.errorNombreUsado);
                        }else{
                            request.setAttribute("mensaje", Constantes.errorRegistroSinValidar);
                        }
                    }      
                break;
                
                case "ACTIVAR": 
                    
                    user = ud.selectCode(cod_act);
                    
                    if(cod_act.equals(user.getCodigo_activacion())) { 
                        
                        LocalDateTime fecha_actual = LocalDateTime.now().minusMinutes(Configuration.getInstance().getTiempoLimite());
                        
                        if((user.getFecha_activacion().isAfter(fecha_actual))){
                            ud.updateActivo(cod_act);
                            request.setAttribute("mensaje", Constantes.mensajeRegistroOk);
                        }else{
                            request.setAttribute("mensaje", Constantes.errorRegistroOk);
                        }          
                    } 
                break;
                
                case "LOGIN":
                    
                    user = ud.selectUser(nombre);
                    
                    if(user != null) {
                        try {
                            if(PasswordHash.getInstance().validatePassword(pwd, user.getPassword())){
                                if(user.getActivo()==1){
                                    request.getSession().setAttribute("sesion",true);
                                    request.setAttribute("mensaje", Constantes.mensajeUsuarioLogueado);
                                }else{
                                    request.setAttribute("mensaje", Constantes.mensajeFaltaActivarCodigo);   
                                }
                            }else{
                                request.setAttribute("mensaje", Constantes.errorUsuarioNoExiste);
                            }
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        request.setAttribute("mensaje", Constantes.errorUsuarioNoExiste);
                    }
                break;
                
                case "CERRAR":
                    
                    request.getSession().setAttribute("sesion",null);
                break;
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
