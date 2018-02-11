package model;

import java.util.Date;

/**
 *
 * @author erasto
 */
public class ApiKey{

    private int ID;
    private String APIKEY;
    private int NUM_PETICIONES;
    private Date FECHA_ULTIMA_PETICION;

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getAPIKEY()
    {
        return APIKEY;
    }

    public void setAPIKEY(String APIKEY)
    {
        this.APIKEY = APIKEY;
    }

    public int getNUM_PETICIONES()
    {
        return NUM_PETICIONES;
    }

    public void setNUM_PETICIONES(int NUM_PETICIONES)
    {
        this.NUM_PETICIONES = NUM_PETICIONES;
    }

    public Date getFECHA_ULTIMA_PETICION()
    {
        return FECHA_ULTIMA_PETICION;
    }

    public void setFECHA_ULTIMA_PETICION(Date FECHA_ULTIMA_PETICION)
    {
        this.FECHA_ULTIMA_PETICION = FECHA_ULTIMA_PETICION;
    }
}
