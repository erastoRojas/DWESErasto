/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;


import dao.TrabajadoresDAO;

import model.Trabajador;

/**
 *
 * @author DAW
 */
public class LoginServicios {

    public Trabajador getTrabajador(Trabajador tr) {
        TrabajadoresDAO dao = new TrabajadoresDAO();
        return dao.getTrabajadorDAO(tr);
    }

    public int crearTrabajador(Trabajador tr) {
        TrabajadoresDAO dao = new TrabajadoresDAO();
        return dao.crearTrabajadorDAO(tr); 
    }
    
}
