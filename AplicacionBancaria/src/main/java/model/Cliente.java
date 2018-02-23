/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author erasto
 */
public class Cliente {
    
    private String cl_dni;
    private String cl_nom;
    private String cl_dir;
    private String cl_tel;
    private String cl_ema;
    private Date cl_fna;
    private Date cl_fcl;
    private int cl_ncu;
    private int cl_sal;
    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCl_dni() {
        return cl_dni;
    }

    public void setCl_dni(String cl_dni) {
        this.cl_dni = cl_dni;
    }

    public String getCl_nom() {
        return cl_nom;
    }

    public void setCl_nom(String cl_nom) {
        this.cl_nom = cl_nom;
    }

    public String getCl_dir() {
        return cl_dir;
    }

    public void setCl_dir(String cl_dir) {
        this.cl_dir = cl_dir;
    }

    public String getCl_tel() {
        return cl_tel;
    }

    public void setCl_tel(String cl_tel) {
        this.cl_tel = cl_tel;
    }

    public String getCl_ema() {
        return cl_ema;
    }

    public void setCl_ema(String cl_ema) {
        this.cl_ema = cl_ema;
    }

    public Date getCl_fna() {
        return cl_fna;
    }

    public void setCl_fna(Date cl_fna) {
        this.cl_fna = cl_fna;
    }

    public Date getCl_fcl() {
        return cl_fcl;
    }

    public void setCl_fcl(Date cl_fcl) {
        this.cl_fcl = cl_fcl;
    }

    public int getCl_ncu() {
        return cl_ncu;
    }

    public void setCl_ncu(int cl_ncu) {
        this.cl_ncu = cl_ncu;
    }

    public int getCl_sal() {
        return cl_sal;
    }

    public void setCl_sal(int cl_sal) {
        this.cl_sal = cl_sal;
    }
    
}
