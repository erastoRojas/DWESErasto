
package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import servicios.AlumnosServicios;

/**
 *
 * @author Erasto Rojas Sánchez
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/secure/alumnos"})

public class Alumnos extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AlumnosServicios as = new AlumnosServicios();
        String op = request.getParameter("op");
        int filas;
        if (op != null){    
            switch (op) {
                case "GETALL":
                    request.setAttribute("alumnos", as.getAllAlumnos());
                    request.getRequestDispatcher("/pintarListaAlumnos.jsp").forward(request, response);
                    break;
                    
                case "INSERT":
                    filas = as.addAlumno(modificarAlumno(request));
                    if(filas != 0){
                        request.setAttribute("mensaje", "se ha insertado "+filas+" alumnos");
                    }else{
                        request.setAttribute("mensaje", "No se ha podido insertar el alumno");
                    }
                    break;
                    
                case "UPDATE":
                    filas = as.updateAlumno(modificarAlumno(request));
                    if(filas != 0){
                        request.setAttribute("mensaje", "Se ha modificado "+filas+" alumnos");
                    }else{
                        request.setAttribute("mensaje", "No se ha podido actualizar el alumno");
                    }
                    break;
                    
                case "DELETE":
                    filas = as.deleteAlumno(modificarAlumno(request));
                    if(filas != 0){
                    request.setAttribute("mensaje", "Se ha borrado "+filas+" alumnos");
                    }else{
                        request.setAttribute("mensaje", "No se ha podido borrar el alumno");
                    }
                    break;
            }
        }
        request.getRequestDispatcher("/pintarListaAlumnos.jsp").forward(request, response);
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
   
    public Alumno modificarAlumno(HttpServletRequest request){
        
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        boolean mayor = request.getParameter("mayor") != null; 
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate local = LocalDate.parse(fecha,dtf);

        Alumno a = new Alumno();
        a.setNombre(nombre);
        a.setFecha_nacimiento(Date.from(local.atStartOfDay().toInstant(ZoneOffset.UTC)));
        a.setMayor_edad(mayor);
        if(!"".equals(request.getParameter("idalumno"))){
            long id = Integer.parseInt(request.getParameter("idalumno"));
            a.setId(id); 
        }
        return a;
    }
}
