/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesDAO;
import model.Cliente;

/**
 *
 * @author DAW
 */
public class LoginServicios {

    public Cliente getCliente(Cliente cl) {
        ClientesDAO dao = new ClientesDAO();
        return dao.getClienteDAO(cl);
    }
    
}
