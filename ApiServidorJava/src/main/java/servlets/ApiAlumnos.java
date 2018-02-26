
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.ApiKey;
import model.MensajeHttp;
import servicios.AlumnosServicios;
import servicios.ApiKeyServicios;

/**
 *
 * @author erasto
 */
@WebServlet(name = "ApiAlumnos", urlPatterns ={"/rest/apiAlumnos"})
public class ApiAlumnos extends HttpServlet {
    
    public AlumnosServicios as = new AlumnosServicios();
    public ApiKeyServicios ak = new ApiKeyServicios();
    
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
        
        //ApiKey k = (ApiKey) request.getAttribute("apikey");
         
        //if(ak.selectApiKey(k) != null){//como hacerlo en el filtro
            List<Alumno> alumnos = new ArrayList<>();
            alumnos =  as.getAllAlumnos();
            request.setAttribute("json", alumnos);
        //}else{
        //    MensajeHttp info = new MensajeHttp("oh, mierda");//preguntar utilidad
        //    request.setAttribute("json", info);
        //}
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        Alumno a = (Alumno) request.getAttribute("alumno");

        if (as.deleteAlumno(a) > 0) {
            MensajeHttp info = new MensajeHttp("Alumno borrado correctamente");
            request.setAttribute("json", info);
        }else{
            MensajeHttp info = new MensajeHttp("Error al borrar");
            request.setAttribute("json", info.getMensaje());
        }  
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        Alumno a = (Alumno) request.getAttribute("alumno");
        
        if (as.addAlumno(a) > 0) {
            
            MensajeHttp info = new MensajeHttp("Alumno insertado correctamente");
            request.setAttribute("json", info.getMensaje());
        }else{
            MensajeHttp info = new MensajeHttp("Error al insertar");
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
        throws ServletException, IOException {
        
        Alumno a = (Alumno) request.getAttribute("alumno");

        if (as.updateAlumno(a) > 0) {
            MensajeHttp info = new MensajeHttp("Alumno modificado correctamente");
            request.setAttribute("json", info);
        }else{
            MensajeHttp info = new MensajeHttp("Error al modificar");
            request.setAttribute("json", info);
        }  
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
