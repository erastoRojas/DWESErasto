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
import model.Alumno;
import model.Asignatura;
import model.MensajeHttp;
import model.Nota;
import servicios.AlumnosServicios;
import servicios.AsignaturasServicios;
import servicios.NotasServicios;

/**
 *
 * @author Erasto Rojas Sánchez
 */
@WebServlet(name = "ApiNotas", urlPatterns =
{
    "/rest/apiNotas"
})
public class ApiNotas extends HttpServlet
{

    public NotasServicios ns = new NotasServicios();
    public AlumnosServicios al = new AlumnosServicios();
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

        List datos = new ArrayList();
        
        datos.add(al.getAllAlumnos());
        datos.add(as.mostrarAsignaturas());
            
        Nota n = (Nota) request.getAttribute("nota");
        
        if(n != null ){ 
           
            Nota nota = ns.getNotas(n.getIdAlumno(),n.getIdAsignatura());
            
            if(nota != null){
                datos.add(nota);
            }else{
                MensajeHttp info = new MensajeHttp("Este alumno no tiene nota");
                datos.add(info);
            }
        }

        request.setAttribute("json", datos);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
          
        Nota n = (Nota) request.getAttribute("nota");
        
        if(n != null ){ 
           
            int hecho = ns.delNota(n);
            
            if(hecho > 0){
                MensajeHttp info = new MensajeHttp("Nota borrada correctamente");
                request.setAttribute("json", info);
            }else{
                MensajeHttp info = new MensajeHttp("No se ha podido borrar la nota");
                request.setAttribute("json", info);
            }
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
          
        Nota n = (Nota) request.getAttribute("nota");
        
        if(n != null ){ 
            
            int hecho = ns.updateNota(n);
            
            if(hecho == 0){
                MensajeHttp info = new MensajeHttp("Nota insertada correctamente");
                request.setAttribute("json", info);
            }else{
                MensajeHttp info = new MensajeHttp("No se ha podido insertar la nota");
                request.setAttribute("json", info);
            }
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
          
        Nota n = (Nota) request.getAttribute("nota");
        
        if(n != null ){ 
           
            int hecho = ns.updateNota(n);
            
            if(hecho > 0){
                MensajeHttp info = new MensajeHttp("Nota actualizada correctamente");
                request.setAttribute("json", info);
            }else{
                MensajeHttp info = new MensajeHttp("No se ha podido actualizar la nota");
                request.setAttribute("json", info);
            }
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
