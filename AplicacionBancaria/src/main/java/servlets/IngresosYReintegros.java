/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.Movimiento;
import servicios.IngresosYReintegrosServicios;

import java.time.*;
import model.Cliente;

/**
 *
 * @author erasto
 */
@WebServlet(name = "IngresosYReintegros", urlPatterns = {"/ingresosYReintegros"})
public class IngresosYReintegros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        IngresosYReintegrosServicios ir = new IngresosYReintegrosServicios();
        
        String n_cuenta = request.getParameter("n_cuenta");

        if(n_cuenta != null){
            
            Cuenta cu = new Cuenta();
            cu.setCu_ncu(n_cuenta);
            
            if(validarCliente(request,n_cuenta)){
                
                cu = ir.getCuenta(cu);
                
                if(cu != null){//chekea en la base de datos que la cuenta existe

                    String op = request.getParameter("op");
                    int filas = 0;
                    
                    if (op != null){    
                        switch (op) {
                            case "INGRESO":
                                
                                int importe = Integer.parseInt(request.getParameter("importe"));
                                String descripcion = request.getParameter("descripcion");
                                String dni = request.getParameter("dni");
                                /*
                                LocalDateTime fechaYHora = LocalDateTime.now();
                                
                                int hora = fechaYHora.getHour();
                                hora += fechaYHora.getMinute();
                                hora += fechaYHora.getSecond();
                                */
                                Movimiento mo = new Movimiento();
                                
                                mo.setMo_ncu(n_cuenta);
                                mo.setMo_imp(importe);
                                mo.setMo_des(descripcion);
                                //mo.setMo_fec(dateFormat);
                                //mo.setMo_hor(hourFormat);
                                
                                filas = ir.crearMovimiento(mo);//crea movimiento
                                if(filas <= 0){
                                    //Error al insertar el movimiento
                                    response.setStatus(500);
                                    response.getWriter().println("Error al insertar el movimiento en la tabla movimientos");
                                    break;
                                }
                                
                                cu.setCu_sal(importe + cu.getCu_sal());
                                filas = ir.updateCuenta(cu);//modifica saldo tabla cuentas
                                if(filas <= 0){
                                    //Error al actualizar el saldo
                                    response.setStatus(500);
                                    response.getWriter().println("Error al actualizar el saldo en la tabla cuentas");
                                    break;
                                }
                                
                                //modifica saldo cliente o clientes tabla clientes
                                Cliente cl = new Cliente();
                                cl.setCl_dni(dni);
                                cl = ir.getCliente(cl);
                                cl.setCl_sal(cl.getCl_sal() + importe);
                                filas = ir.updateCliente(cl);
                                if(filas <= 0){
                                    //Error al modificar el saldo en cliente
                                    response.setStatus(500);
                                    response.getWriter().println("Error al actualizar el saldo en la tabla clientes");
                                }
                                
                                break;
                                
                            case "REINTEGRO":
                                break;
                        }
                    }else{
                       //error
                    response.setStatus(500);
                    response.getWriter().println("error"); 
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
            //request.setAttribute("clientes", cs.getAllClientes());
            request.getRequestDispatcher("ingresosyreintegros.jsp").forward(request, response);
        }else{
            //manda a pagina de inicio
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
