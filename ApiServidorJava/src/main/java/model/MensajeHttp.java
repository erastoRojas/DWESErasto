/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class MensajeHttp {
    
    private String mensaje;

    public MensajeHttp(String mensaje) {
        this.mensaje = mensaje;
    }

    public MensajeHttp()
    {
        //CONSTRUCTOR
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
