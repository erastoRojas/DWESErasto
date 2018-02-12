/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class UserWS {
    
    private String user;
    private ArrayList<String> productos;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<String> getRoom() {
        return productos;
    }

    public void setRoom(ArrayList<String> room) {
        this.productos = room;
    }
    
    public boolean buscaRoom(String producto)
    {
        return this.productos.contains(producto);
    }
    
}
