/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesDAO;
import dao.CuentasDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.Cliente;
import model.Cuenta;
import servlets.AperturaCuentas;

/**
 *
 * @author erasto
 */
public class AperturaDeCuentasServicios
{

    public boolean comprobarDni(String dni) {
    
        int cont = 0;
        for(int i = 0; i < dni.length(); i++){
            cont++;
        }
        if(cont != 9){
            return false;
        }else return !isNumeric(dni);
    }
    
    private static boolean isNumeric (String cadena){
        
        String letra = cadena.substring(8,9);
        try{
            Integer.parseInt (letra);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
        
    }
    
    public boolean validarCliente(String n_cuenta){//comprueba el numero de cuenta
        
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

    public Cliente getCliente(Cliente cl) {
        ClientesDAO dao = new ClientesDAO();
        return dao.getClienteDAO(cl);
    }

    public Cuenta getCuenta(Cuenta cu) {
        CuentasDAO dao = new CuentasDAO();
        return dao.getCuentaDAO(cu);
    } 

    public int crearCliente(Cliente cl) {
        ClientesDAO dao = new ClientesDAO();
        return dao.insertClienteDAO(cl);
    }

    public Date getFechaNacimiento(String cl_fna) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_nac = null;
        try {
            fecha_nac = sdf.parse(cl_fna);
        } catch (ParseException ex) {
            Logger.getLogger(AperturaCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fecha_nac;
    }

    public int crearCuenta(Cuenta cu) {
        CuentasDAO dao = new CuentasDAO();
        return dao.crearCuentaDAO(cu);
    }
    
}
