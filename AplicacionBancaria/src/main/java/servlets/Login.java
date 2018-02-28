/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Trabajador;
import servicios.LoginServicios;

/**
 *
 * @author erasto
 */
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
        String pass = request.getParameter("pass");
        String nombre = request.getParameter("nombre");
         
        LoginServicios ls = new LoginServicios();
        Trabajador tr = new Trabajador();
        
        if (op != null){    
            switch (op) {  
                    
                case "LOGIN":
                    
                    tr.setTr_id(pass);
                    tr.setTr_id(nombre);
                    tr = ls.getTrabajador(tr);
                    
                    if(tr != null){
                        if(tr.getTr_id().equals(pass)){
                            request.getSession().setAttribute("sesion",true);
                            //variable session con el dni
                            request.setAttribute("usuario", tr.getTr_no());
                            request.getRequestDispatcher("menuInicio.jsp").forward(request, response);
                            break;
                        }else{
                            //Contraseña inválida
                            response.setStatus(500);
                            response.getWriter().println("Contraseña inválida");
                            break;
                        }  
                    }else{
                        //El usuario no existe
                        response.setStatus(500);
                        response.getWriter().println("Este cliente no existe");
                        break;
                    }
                
                case "REGISTRAR":
                    
                    int filas = 0;
                    
                    tr.setTr_id(pass);
                    tr.setTr_no(nombre);
                    
                    filas = ls.crearTrabajador(tr);
                    
                    if(filas > 0){
                        response.getWriter().println("Trabajador creado correctamente");
                    }else{
                        //No se ha podido registrar
                        response.setStatus(500);
                        response.getWriter().println("No ha sido posible registrar al trabajdor"); 
                    }
                    
                    break;
                    
                case "CERRAR":
                    
                    request.getSession().setAttribute("sesion",null);
                    
                break;    
            }
        }
                   
                    
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
