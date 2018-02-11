package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Nota;
import servicios.AlumnosServicios;
import servicios.AsignaturasServicios;
import servicios.NotasServicios;

/**
 *
 * @author Erasto Rojas Sánchez
 */
@WebServlet(name = "Notas", urlPatterns = {"/secure/notas"})
public class Notas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        NotasServicios ns = new NotasServicios();
        AlumnosServicios al = new AlumnosServicios();
        AsignaturasServicios as = new AsignaturasServicios();
        
        request.setAttribute("alumnos", al.getAllAlumnos());
        request.setAttribute("asignaturas", as.mostrarAsignaturas());
        
        String op = request.getParameter("op");
        String idAlumno = request.getParameter("idAlumno");
        String nombreAlu = request.getParameter("nombreAlu");
        String idAsig = request.getParameter("idAsig");
        String nombreAsig = request.getParameter("nombreAsig");
        String nota = request.getParameter("nota");
 
        if (op != null) {
            Nota n = new Nota();
            n.setIdAlumno(Long.parseLong(idAlumno));
            n.setIdAsignatura(Long.parseLong(idAsig));
        
            switch (op) {
                
                case "cargar":
                    n = ns.getNotas(n.getIdAlumno(), n.getIdAsignatura());
                    request.setAttribute("nota", n);     
                    break;
                    
                case "guardar"://guarda y/o actualiza
                    n.setNota(Integer.parseInt(nota));
                    ns.updateNota(n);
                    request.setAttribute("nota", n);
                    break;
                case "borrar":
                    n.setNota(Integer.parseInt(nota));
                    ns.delNota(n);
                    break;
            }     
        }
        request.setAttribute("nAsig", nombreAsig);
        request.setAttribute("nomAlu", nombreAlu);
        request.setAttribute("idAlu", idAlumno);
        request.setAttribute("idAsig", idAsig);
        request.getRequestDispatcher("/pintarListaNotas.jsp").forward(request, response);      
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
