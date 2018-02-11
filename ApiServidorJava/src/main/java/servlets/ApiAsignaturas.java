/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Asignatura;
import model.MensajeHttp;
import servicios.AsignaturasServicios;

/**
 *
 * @author erasto
 */
@WebServlet(name = "ApiAsignaturas", urlPatterns ={"/rest/apiAsignaturas"})
public class ApiAsignaturas extends HttpServlet{
    
    public AsignaturasServicios as = new AsignaturasServicios();

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
        
        List<Asignatura> asignaturas = new ArrayList<>();
        asignaturas =  as.mostrarAsignaturas();
        request.setAttribute("json", asignaturas);
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        
        if(as.deleteAsignatura(a) > 0){
            MensajeHttp info = new MensajeHttp("Asignatura borrada correctamente");
            request.setAttribute("json", info);
        }else{
            MensajeHttp info = new MensajeHttp("No se ha podido borrar la asignatura");
            request.setAttribute("json", info);
        }
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        
        if(as.addAsignatura(a) != null){
            MensajeHttp info = new MensajeHttp("Asignatura insertada correctamente");
            request.setAttribute("json", info);
        }else{
            MensajeHttp info = new MensajeHttp("No se ha podido insertar la asignatura");
            request.setAttribute("json", info);
        }
        
        
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
        throws ServletException, IOException{
        
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        
        if(as.updateAsignatura(a) > 0){
            MensajeHttp info = new MensajeHttp("Asignatura actualizada correctamente");
            request.setAttribute("json", info);
        }else{
            MensajeHttp info = new MensajeHttp("No se ha podido actualizar la asignatura");
            request.setAttribute("json", info);
        }
      
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

}
