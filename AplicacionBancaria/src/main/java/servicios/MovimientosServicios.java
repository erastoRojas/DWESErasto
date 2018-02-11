/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.MovimientosDAO;
import java.util.Date;
import java.util.List;
import model.Movimiento;

/**
 *
 * @author erasto
 */
public class MovimientosServicios
{
    public List<Movimiento> getAllMovimientos() {
        MovimientosDAO dao = new MovimientosDAO();
        return dao.getAllMovimientos();
    }
    
    public List<Movimiento> getMovimientos(Movimiento v,Date date1, Date date2) {
        MovimientosDAO dao = new MovimientosDAO();
        return dao.getMovimientosDAO(v,date1,date2);
    }
    
    public Movimiento getCuenta(Movimiento v) {
        MovimientosDAO dao = new MovimientosDAO();
        return dao.getCuentaDAO(v);
    }
    
}
