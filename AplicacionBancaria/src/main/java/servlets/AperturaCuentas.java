/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Cuenta;
import servicios.AperturaDeCuentasServicios;

/**
 *
 * @author erasto
 */
@WebServlet(name = "AperturaCuentas", urlPatterns = {"/secure/aperturaCuentas"})
public class AperturaCuentas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AperturaDeCuentasServicios ac = new AperturaDeCuentasServicios();
        
        String dni = request.getParameter("dni");
        String n_cuenta = request.getParameter("n_cuenta");
        String cl_nom = request.getParameter("cl_nom");
        String cl_dni = request.getParameter("cl_dni");
        String cl_dir = request.getParameter("cl_dir");
        String cl_tel = request.getParameter("cl_tel");
        String cl_ema = request.getParameter("cl_ema");
        String cl_fna = request.getParameter("cl_fna");
        String cl_fcl = request.getParameter("cl_fcl");
        String cl_ncu = request.getParameter("cl_ncu");
        String cl_sal = request.getParameter("cl_sal");
        String saldo_apertura = request.getParameter("saldo_apertura");
        String titular2 = request.getParameter("miLista");
        String op = request.getParameter("op");
        //datos 2 titular
        String dni2 = request.getParameter("dni2");
        String cl_nom2 = request.getParameter("cl_nom2");
        String cl_dir2 = request.getParameter("cl_dir2");
        String cl_tel2 = request.getParameter("cl_tel2");
        String cl_ema2 = request.getParameter("cl_ema2");
        String cl_fna2 = request.getParameter("cl_fna2");
        String cl_fcl2 = request.getParameter("cl_fcl2");
        String cl_ncu2 = request.getParameter("cl_ncu2");
        String cl_sal2 = request.getParameter("cl_sal2");
        
        if (op != null){    
            switch (op) {
                case "DNI":
                    
                    if(dni != null){
                        
                        if(ac.comprobarDni(dni)){

                            Cliente cl = new Cliente();
                            cl.setCl_dni(dni);

                            cl = ac.getCliente(cl);//servicios

                            if(cl != null){
                                String json = new Gson().toJson(cl);
                                response.getWriter().print(json);
                            }else{
                                //Cliente no existe
                                request.getSession().setAttribute("cliente",false);
                                response.setStatus(500);
                                response.getWriter().println("No existe el cliente");
                                break;
                            }
                        }else{
                            //Dni incorrecto
                            response.setStatus(500);
                            response.getWriter().println("Dni incorrecto");
                            break;
                        }
                    }else{
                        //error
                        response.setStatus(500);
                        response.getWriter().println("falta parametro dni");
                    }
                    
                break;  
                
                case "CUENTA":
                Cuenta cu = new Cuenta();
                Cuenta cuAux = new Cuenta();
                Cliente cl = new Cliente();
                Cliente cl2 = new Cliente();
                Boolean creado = false;
                Boolean creado2 = false;
                int filas = 0;

                if(n_cuenta != null){    
                    
                    if(ac.validarCliente(n_cuenta)){
                        
                        
                        
                        cuAux.setCu_ncu(n_cuenta);
                        cuAux = ac.getCuenta(cuAux);//chekea en la base de datos que la cuenta existe

                            if(cuAux == null){
                                if(saldo_apertura != null){
                                    
                                    int saldo = Integer.parseInt(saldo_apertura);
                                    
                                    if(saldo > 0){//chekea que el saldo sea mayor que 0
                                        
                                        if(request.getSession().getAttribute("cliente") != null){//si el cliente no existe lo añade
                                            
                                            Date date = new Date();
                                            
                                            cl.setCl_dni(cl_dni);
                                            cl.setCl_nom(cl_nom);
                                            cl.setCl_dir(cl_dir);
                                            cl.setCl_tel(cl_tel);
                                            cl.setCl_ema(cl_ema);
                                            cl.setCl_fna(ac.getFechaNacimiento(cl_fna));//llama a servicios y devuelve la fecha
                                            cl.setCl_fcl(date);
                                            cl.setCl_ncu(Integer.parseInt(cl_ncu));
                                            cl.setCl_sal(saldo); 
                                            
                                            filas = ac.crearCliente(cl);
                                            
                                            if(filas > 0 ){
                                                creado = true;
                                            }else{
                                                //Cliente no se ha podido crear
                                                response.setStatus(500);
                                                response.getWriter().println("No se ha podido crear el cliente");
                                                break;
                                            }
                                        
                                        }else{
                                            cl.setCl_dni(cl_dni);
                                            cl = ac.getCliente(cl);
                                        }
                                        
                                        int tit2 = Integer.parseInt(titular2);
                                        
                                        if(tit2 == 1){//chekea la creacion del 2titular
                                            
                                            Cliente clienteAux = new Cliente();
                                            
                                            clienteAux.setCl_dni(dni2);
                                            clienteAux = ac.getCliente(cl2);//servicios chekea que existe
                                            
                                            if(clienteAux == null){
                                            
                                                Date date2 = new Date();
                                                
                                                cl2.setCl_dni(dni2);
                                                cl2.setCl_dni("hola");
                                                cl2.setCl_nom(cl_nom2);
                                                cl2.setCl_dir(cl_dir2);
                                                cl2.setCl_tel(cl_tel2);
                                                cl2.setCl_ema(cl_ema2);
                                                cl2.setCl_fna(ac.getFechaNacimiento(cl_fna2));//llama a servicios y devuelve la fecha
                                                cl2.setCl_fcl(date2);
                                                cl2.setCl_ncu(Integer.parseInt(cl_ncu2));
                                                cl2.setCl_sal(saldo);

                                                filas = 0;
                                                filas = ac.crearCliente(cl2);

                                                if(filas > 0 ){

                                                    creado2 = true;
                                                }else{
                                                    //Cliente no se ha podido crear
                                                    response.setStatus(500);
                                                    response.getWriter().println("No se ha podido crear el cliente");
                                                    break;
                                                }
                                            } 
                                        }
                                        
                                        if(creado){
                                            
                                            cu.setCu_dn1(cl.getCl_dni());
                                            
                                            if(creado2){
                                                cu.setCu_dn2(cl2.getCl_dni());
                                            }
                                            
                                            cu.setCu_ncu(n_cuenta);
                                            cu.setCu_sal(saldo);
                                            
                                            filas = 0;
                                            filas = ac.crearCuenta(cu);
                                            
                                            if(filas > 0){
                                                
                                                //saldo menor que 0
                                                response.setStatus(500);
                                                response.getWriter().println("todo ok");
                                            
                                            }else{
                                                //Cliente no se ha podido crear
                                                response.setStatus(500);
                                                response.getWriter().println("No se ha podido crear la Cuenta");
                                                break;
                                            }
                                        }

                                    }else{
                                        //saldo menor que 0
                                        response.setStatus(500);
                                        response.getWriter().println("El saldo es menor que 0");
                                }
                                }else{
                                    //No hay saldo
                                    response.setStatus(500);
                                    response.getWriter().println("Debes indicar un saldo inicial");
                                }
                            }else{
                                //Cuenta existente
                                response.setStatus(500);
                                response.getWriter().println("Esta Cuenta ya existe");
                            }
                    } else{
                        //Mal fomato cuenta
                        response.setStatus(500);
                        response.getWriter().println("Mal formato de cuenta");
                    }
                
                }else{
                    //Mal fomato cuenta
                    response.setStatus(500);
                    response.getWriter().println("Falta parametro numero de cuenta");    
                }
                
                break;
        }
        
        
        
        }
              
        if(op == null){
            //manda a pagina de inicio
            request.getRequestDispatcher("/aperturaCuentas.jsp").forward(request, response);
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

}
