package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Movimiento;
import servicios.MovimientosServicios;

/**
 *
 * @author erasto
 */
@WebServlet(name = "Movimientos", urlPatterns = {"/movimientos"})//          /secure
public class Movimientos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MovimientosServicios ms = new MovimientosServicios();
        List<Movimiento> lista = null;
        boolean bole = true;
        
        String n_cuenta = request.getParameter("n_cuenta");

        if(n_cuenta != null){
            
            Movimiento mo = new Movimiento();
            mo.setMo_ncu(n_cuenta);
            
            if(validarCliente(request,n_cuenta)){
                if(ms.getCuenta(mo)!= null){
                
                    String startDateStr = request.getParameter("fecha_inicio");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha_inicio = null;
                    try {
                        fecha_inicio = sdf.parse(startDateStr);
                    } catch (ParseException ex) {
                        Logger.getLogger(Movimientos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String startDateStr2 = request.getParameter("fecha_fin");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha_fin = null;
                    try {
                        fecha_fin = sdf2.parse(startDateStr2);

                    } catch (ParseException ex) {
                        Logger.getLogger(Movimientos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        lista = ms.getMovimientos(mo,fecha_inicio,fecha_fin);
                        
                        if(lista.isEmpty()){
                            //Cuenta sin movimientos entre las fechas especificadas
                            response.setStatus(500);
                            response.getWriter().println("Cuenta sin movimientos entre las fechas especificadas");
                            bole = false;
                        }
                }else{
                    //Cuenta inexistente
                    response.setStatus(500);
                    response.getWriter().println("Cuenta no aparece en base de datos");
                }
            }else{
                //Mal fomato cuenta
                response.setStatus(500);
                response.getWriter().println("Mal formato de cuenta");
            }
        }else{
            
        }
        if(lista != null && bole == true){ 
            String json = new Gson().toJson(lista);
            response.getWriter().print(json);
            
        }
        if(n_cuenta == null){
            //manda a pagina de inicio
            request.getRequestDispatcher("movimientos.jsp").forward(request, response);
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
