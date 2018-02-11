package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Asignatura;
import servicios.AsignaturasServicios;

/**
 *
 * @author Erasto Rojas Sánchez
 */
@WebServlet(name = "Asignaturas", urlPatterns = {"/secure/asignaturas"})
public class Asignaturas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AsignaturasServicios as = new AsignaturasServicios();
        String op = request.getParameter("op");
        
        if (op != null){    
            switch (op) {
                case "GETALL":
                    request.setAttribute("asignaturas", as.mostrarAsignaturas());
                    request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);
                    break;
                    
                case "INSERT":
                    as.addAsignatura(modificarAsignatura(request));
                    request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);
                    break;
                    
                case "UPDATE":
                    as.updateAsignatura(modificarAsignatura(request));
                    request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);
                    break;
                    
                case "DELETE":
                    as.deleteAsignatura(modificarAsignatura(request));
                    request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);
                    break;
            }
        }else {
            request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);}
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

    public Asignatura modificarAsignatura(HttpServletRequest request){
        String nombre = request.getParameter("nombre");
        String curso = request.getParameter("curso");
        String ciclo = request.getParameter("ciclo"); 

        Asignatura a = new Asignatura();
        a.setNombre(nombre);
        a.setCurso(curso);
        a.setCiclo(ciclo);
        
        if(!"".equals(request.getParameter("idasignatura"))){
            long id = Integer.parseInt(request.getParameter("idasignatura"));
            a.setId(id);
        }
        return a;
    }
}
