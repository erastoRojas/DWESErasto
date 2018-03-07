/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesAPI;
import dao.CuentasAPI;
import dao.MovimientosAPI;
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
        CuentasAPI dao = new CuentasAPI();
        return dao.getCuentaDAO(cu);
    }

    public int crearMovimiento(Movimiento mo) {
        MovimientosAPI dao = new MovimientosAPI();
        return dao.crearMovimientoDAO(mo);
    }

    public int updateCuenta(Cuenta cu) {
        CuentasAPI dao = new CuentasAPI();
        return dao.updateCuentaDAO(cu);
    }
    
    public Cliente getCliente(Cliente cl){
        ClientesAPI dao = new ClientesAPI();
        return dao.getClienteDAO(cl);
    }

    public int updateCliente(Cliente cl){
        ClientesAPI dao = new ClientesAPI();
        return dao.updateClienteDAO(cl);
    }

    public Cuenta getSaldo(Cuenta cu){
        CuentasAPI dao = new CuentasAPI();
        return dao.getSaldoDAO(cu);
    }
}
