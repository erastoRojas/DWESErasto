/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import servicios.LoginServicios;

/**
 *
 * @author DAW
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
        String dni = request.getParameter("dni");
        String pass = request.getParameter("pass");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String fecha_nac = request.getParameter("fecha_nac");
        String fecha_cuen = request.getParameter("fecha_cuen");
        String num_cuentas = request.getParameter("num_cuentas");
        String saldo = request.getParameter("saldo");
         
        LoginServicios ls = new LoginServicios();
        
        if (op != null){    
            switch (op) {  
                    
                case "LOGIN":
                    
                    Cliente cl = new Cliente();
                    cl.setCl_dni(dni);
                    cl = ls.getCliente(cl);
                    
                    if(cl != null){
                        if(cl.getPass().equals(pass)){
                            request.getSession().setAttribute("sesion",true);
                            //variable session con el dni
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
