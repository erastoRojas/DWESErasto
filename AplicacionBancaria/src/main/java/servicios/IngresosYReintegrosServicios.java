/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesDAO;
import dao.CuentasDAO;
import dao.MovimientosDAO;
import model.Cliente;
import model.Cuenta;
import model.Movimiento;

/**
 *
 * @author erasto
 */
public class IngresosYReintegrosServicios
{

    public Cuenta getCuenta(Cuenta cu) {
        CuentasDAO dao = new CuentasDAO();
        return dao.getCuentaDAO(cu);
    }

    public int crearMovimiento(Movimiento mo) {
        MovimientosDAO dao = new MovimientosDAO();
        return dao.crearMovimientoDAO(mo);
    }

    public int updateCuenta(Cuenta cu) {
        CuentasDAO dao = new CuentasDAO();
        return dao.updateCuentaDAO(cu);
    }
    
    public Cliente getCliente(Cliente cl){
        ClientesDAO dao = new ClientesDAO();
        return dao.getClienteDAO(cl);
    }

    public int updateCliente(Cliente cl){
        ClientesDAO dao = new ClientesDAO();
        return dao.updateClienteDAO(cl);
    }

    public Cuenta getSaldo(Cuenta cu){
        CuentasDAO dao = new CuentasDAO();
        return dao.getSaldoDAO(cu);
    }
}
