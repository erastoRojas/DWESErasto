/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.Movimiento;
import servicios.IngresosYReintegrosServicios;

import java.time.*;
import java.util.Date;
import model.Cliente;

/**
 *
 * @author erasto                                             
 */
@WebServlet(name = "IngresosYReintegros", urlPatterns = {"/secure/ingresosYReintegros"})
public class IngresosYReintegros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        IngresosYReintegrosServicios ir = new IngresosYReintegrosServicios();
        
        String n_cuenta = request.getParameter("n_cuenta");

        if(n_cuenta != null){
            
            int importe = Integer.parseInt(request.getParameter("importe"));
            String descripcion = request.getParameter("descripcion");
            String dni = "11111111A";//request.getParameter("dni");//variable se sesion

            Date date = new Date();

            DateFormat horas = new SimpleDateFormat("HH:mm:ss");
            String hora = horas.format(date);
            hora = horas(request,hora);

            boolean bole = true;
            
            Movimiento mo = new Movimiento();
            Cuenta cu = new Cuenta();
            Cliente cl = new Cliente();
            
            cu.setCu_ncu(n_cuenta);
            
            if(validarCliente(request,n_cuenta)){
                
                cu = ir.getCuenta(cu);
                
                if(cu != null){//chekea en la base de datos que la cuenta existe

                    String op = request.getParameter("op");
                    int filas = 0;
                    
                    if (op != null){    
                        switch (op) {
                            case "INGRESO":
                                
                                //modifica saldo tabla cuentas
                                cu.setCu_sal(importe + cu.getCu_sal());
                                filas = ir.updateCuenta(cu);
                                if(filas == 0){
                                    //Error al actualizar el saldo
                                    bole = false;
                                    response.setStatus(500);
                                    response.getWriter().println("Error al actualizar el saldo en la tabla cuentas");
                                    break;
                                }
                                
                                //modifica saldo cliente o clientes tabla clientes
                                cl.setCl_dni(dni);
                                cl = ir.getCliente(cl);
                                cl.setCl_sal(cl.getCl_sal() + importe);
                                filas = ir.updateCliente(cl);
                                if(filas <= 0){
                                    //Error al modificar el saldo en cliente
                                    bole = false;
                                    response.setStatus(500);
                                    response.getWriter().println("Error al actualizar el saldo en la tabla clientes");
                                    break;
                                }
                                //crea movimiento
                                mo.setMo_ncu(n_cuenta);
                                mo.setMo_imp(importe);
                                mo.setMo_des(descripcion);
                                mo.setMo_fec(date);
                                mo.setMo_hor(hora);
                                mo.setMo_sal(cu.getCu_sal());
                                
                                filas = ir.crearMovimiento(mo);
                                if(filas == 0){
                                    //Error al insertar el movimiento
                                    bole = false;
                                    response.setStatus(500);
                                    response.getWriter().println("Error al insertar el movimiento en la tabla movimientos");
                                    break;
                                }
                                
                                if(bole == true){
                                    response.getWriter().println("Ingreso realizado correctamente!!!!");
                                }
                                
                                break;
                                
                            case "REINTEGRO":
                                
                                cu.setCu_sal(importe);
                                Cuenta CuentaAux = ir.getCuenta(cu);
                                if(CuentaAux.getCu_sal() < cu.getCu_sal()){//Chekea que tiene mas de lo que va a sacar
                                    //Error al modificar el saldo en cliente
                                    bole = false;
                                    response.setStatus(500);
                                    response.getWriter().println("No tienes suficiente dinero para retirar");
                                }else{
  
                                    //modifica saldo tabla cuentas
                                    CuentaAux.setCu_sal(CuentaAux.getCu_sal() - importe);
                                    filas = ir.updateCuenta(CuentaAux);
                                    if(filas == 0){
                                        //Error al actualizar el saldo
                                        bole = false;
                                        response.setStatus(500);
                                        response.getWriter().println("Error al actualizar el saldo en la tabla cuentas");
                                        break;
                                    }
                                    //modifica saldo cliente o clientes tabla clientes
                                    cl.setCl_dni(dni);
                                    cl = ir.getCliente(cl);
                                    cl.setCl_sal(cl.getCl_sal() - importe);
                                    filas = ir.updateCliente(cl);
                                    if(filas <= 0){
                                        //Error al modificar el saldo en cliente
                                        bole = false;
                                        response.setStatus(500);
                                        response.getWriter().println("Error al actualizar el saldo en la tabla clientes");
                                    }
                                    
                                    //crea movimiento
                                    mo.setMo_ncu(n_cuenta);
                                    mo.setMo_imp(-importe);
                                    mo.setMo_des(descripcion);
                                    mo.setMo_fec(date);
                                    mo.setMo_hor(hora);
                                    mo.setMo_sal(CuentaAux.getCu_sal());
                                    
                                    filas = ir.crearMovimiento(mo);
                                    if(filas == 0){
                                        //Error al insertar el movimiento
                                        bole = false;
                                        response.setStatus(500);
                                        response.getWriter().println("Error al insertar el movimiento en la tabla movimientos");
                                        break;
                                    }

                                    if(bole == true){
                                        response.getWriter().println("Reintegro realizado correctamente!!!!");
                                    }
                                }
                                break;
                        }
                    }else{
                    //error
                    bole = false;
                    response.setStatus(500);
                    response.getWriter().println("error"); 
                    }
                }else{
                    //Cuenta inexistente
                    bole = false;
                    response.setStatus(500);
                    response.getWriter().println("Cuenta no aparece en base de datos");
                }
            }else{
                //Mal fomato cuenta
                bole = false;
                response.setStatus(500);
                response.getWriter().println("Mal formato de cuenta");
            }
        }
        
        if(n_cuenta == null){
            //manda a pagina de inicio
            request.getRequestDispatcher("/ingresosyreintegros.jsp").forward(request, response);
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
        boolean bole = true;
        
        for(int i = 0; i < n_cuenta.length()-1; i++){
            suma_num += Character.getNumericValue(n_cuenta.charAt(i));
            cont++;
        }
        if((cont+1) != 10){
            bole = false;
        }else{
            String foo = n_cuenta.substring(9,10);
            int ult_num = Integer.parseInt(foo);
            
            if(suma_num % 9 != ult_num){
                bole = false;
            }
        }   
        return bole;
    }
    
    public String horas(HttpServletRequest request,String horas){
        
        String aux = "";
                                
        for(int i = 0; i < horas.length(); i++){
            if(horas.charAt(i) != ':'){
                aux += horas.charAt(i);
            }
        }
        return aux;
    }
}
