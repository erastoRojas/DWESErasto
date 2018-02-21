/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.IngresosYReintegrosDAO;
import model.Cuenta;

/**
 *
 * @author erasto
 */
public class IngresosYReintegrosServicios
{

    public Cuenta getCuenta(Cuenta cu) {
        IngresosYReintegrosDAO dao = new IngresosYReintegrosDAO();
        return dao.getCuentaDAO(cu);
    }
    
}
