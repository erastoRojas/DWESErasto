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
import model.Cliente;
import model.Cuenta;
import model.MensajeHttp;
import model.Movimiento;
import servicios.ClientesServicios;
import servicios.IngresosYReintegrosServicios;

/**
 *
 * @author Eduardo DAW
 */
@WebServlet(name = "IngresosYReintegrosREST", urlPatterns ={"/rest/IngresosYReintegrosREST"})
public class IngresosYReintegrosREST extends HttpServlet
{
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    ClientesServicios cs = new ClientesServicios();
    IngresosYReintegrosServicios ir = new IngresosYReintegrosServicios();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
        Cliente getCliente = (Cliente) request.getAttribute("getCliente");
        Cuenta getCuenta = (Cuenta) request.getAttribute("getCuenta");
        Cuenta getSaldo = (Cuenta) request.getAttribute("getSaldo");
        String op = (String) request.getAttribute("op");

        if (op != null){    
            switch (op) {
                case "getCliente":
                    
                    if(getCliente != null){
                        getCliente = ir.getCliente(getCliente);

                        if(getCliente != null){
                            //MensajeHttp info = new MensajeHttp("Alumno modificado correctamente");
                            request.setAttribute("json", getCliente); 
                        }else{
                            response.setStatus(503);
                            MensajeHttp info = new MensajeHttp("error case getCliente");
                            request.setAttribute("json", info);
                        }
                    }else{
                        response.setStatus(503);
                        MensajeHttp info = new MensajeHttp("error case getCliente2");
                        request.setAttribute("json", info);
                    }
                    
                    break;
 
                case "getCuenta":
                    
                    if(getCuenta != null){
            
                        getCuenta = ir.getCuenta(getCuenta);//no sera mejor devolver un boolean

                        if(getCuenta != null){
                            //MensajeHttp info = new MensajeHttp("Alumno modificado correctamente");
                            request.setAttribute("json", getCuenta);
                        }else{
                            response.setStatus(503);
                            MensajeHttp info = new MensajeHttp("error getCuenta");
                            request.setAttribute("json", info);
                        }

                    }else{
                        response.setStatus(503);
                        MensajeHttp info = new MensajeHttp("error getCuenta2");
                        request.setAttribute("json", info);
                    }
                    
                    break;
                    
                    case "getSaldo":
                    
                    if(getSaldo != null){
            
                        getSaldo = ir.getCuenta(getSaldo);//no sera mejor devolver un boolean

                        if(getCuenta != null){
                            //MensajeHttp info = new MensajeHttp("Alumno modificado correctamente");
                            request.setAttribute("json", getSaldo);
                        }else{
                            response.setStatus(503);
                            MensajeHttp info = new MensajeHttp("error getSaldo");
                            request.setAttribute("json", info);
                        }

                    }else{
                        response.setStatus(503);
                        MensajeHttp info = new MensajeHttp("error getSaldo2");
                        request.setAttribute("json", info);
                    }
                    
                    break;
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
            throws ServletException, IOException
    {
        Cuenta updateCuenta = (Cuenta) request.getAttribute("updateCuenta");
        Cliente updateCliente = (Cliente) request.getAttribute("updateCliente");
        Movimiento crearMovimiento = (Movimiento) request.getAttribute("crearMovimiento");
        String op = (String) request.getAttribute("op");
        
        int lineas = 0;
        
        if (op != null){    
            switch (op) {
        
                case "updateCuenta":
                    
                    if(updateCuenta != null){
            
                        lineas = ir.updateCuenta(updateCuenta);//no sera mejor devolver un boolean

                        if(lineas > 0){
                            //MensajeHttp info = new MensajeHttp("Alumno modificado correctamente");
                            request.setAttribute("json", lineas);
                        }else{
                            response.setStatus(503);
                            MensajeHttp info = new MensajeHttp("error updateCuenta");
                            request.setAttribute("json", info);
                        }

                    }else{
                        response.setStatus(503);
                        MensajeHttp info = new MensajeHttp("errorUpdateCuenta 2");
                        request.setAttribute("json", info);
                    }
                    
                break;
                    
                case "updateCliente":
                    
                    if(updateCliente != null){
            
                        lineas = ir.updateCliente(updateCliente);//no sera mejor devolver un boolean

                        if(lineas > 0){
                            //MensajeHttp info = new MensajeHttp("Alumno modificado correctamente");
                            request.setAttribute("json", updateCliente);
                        }else{
                            response.setStatus(503);
                            MensajeHttp info = new MensajeHttp("error updateCliente");
                            request.setAttribute("json", info);
                        }

                    }else{
                        response.setStatus(503);
                        MensajeHttp info = new MensajeHttp("error updateCliente2");
                        request.setAttribute("json", info);
                    }
                    
                break;    
                    
                case "crearMovimiento":

                if(crearMovimiento != null){

                    lineas = ir.crearMovimiento(crearMovimiento);//no sera mejor devolver un boolean

                    if(lineas > 0){
                        //MensajeHttp info = new MensajeHttp("Alumno modificado correctamente");
                        request.setAttribute("json", crearMovimiento);
                    }else{
                        response.setStatus(503);
                        MensajeHttp info = new MensajeHttp("error crearMovimiento");
                        request.setAttribute("json", info);
                    }

                }else{
                    response.setStatus(503);
                    MensajeHttp info = new MensajeHttp("error crearMovimiento2");
                    request.setAttribute("json", info);
                }

                break;
            }
        }
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
       
        
        
        
        
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
