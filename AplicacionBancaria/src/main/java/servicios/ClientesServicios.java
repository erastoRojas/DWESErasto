/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesDAO;
import java.util.List;
import model.Cliente;

/**
 *
 * @author erasto
 */
public class ClientesServicios {

    public List<Cliente> mostrarClientes() {
        ClientesDAO dao = new ClientesDAO();
        return dao.getAllClientesDBUils();
    }
    
}
