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
import model.Cuenta;
import servicios.IngresosYReintegrosServicios;

/**
 *
 * @author erasto
 */
@WebServlet(name = "IngresosYReintegros", urlPatterns = {"/ingresosYReintegros"})
public class IngresosYReintegros extends HttpServlet {

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
    
        IngresosYReintegrosServicios ir = new IngresosYReintegrosServicios();
        
        String n_cuenta = request.getParameter("n_cuenta");

        if(n_cuenta != null){
            
            Cuenta cu = new Cuenta();
            cu.setCu_ncu(n_cuenta);
            
            if(validarCliente(request,n_cuenta)){
                if(ir.getCuenta(cu)!= null){//chekea en la base de datos que la cuenta existe
                    String op = request.getParameter("op");
                    //y entoces hago el switch case
                }
            }
        
            //request.setAttribute("clientes", cs.getAllClientes());
            request.getRequestDispatcher("ingresosyreintegros.jsp").forward(request, response);
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
    
    public boolean validarCliente(HttpServletRequest request,String n_cuenta){
        
        int cont = 0;
        int suma_num = 0;
        String foo = n_cuenta.substring(9,10);
        int ult_num = Integer.parseInt(foo);
        boolean bole = true;
        
        for(int i = 0; i < n_cuenta.length()-1; i++){
            suma_num += Character.getNumericValue(n_cuenta.charAt(i));
            cont++;
        }
        if((cont+1) != 10){
            bole = false;
        }else if(suma_num % 9 != ult_num){
            bole = false;
        }   
        return bole;
    }
}
