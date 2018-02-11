package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author erasto
 */
public class User {
    private int id;
    private String nombre;
    private String password;
    private int activo;
    private String codigo_activacion;
    private LocalDateTime fecha_activacion;

    public LocalDateTime getFecha_activacion() {
        return fecha_activacion;
    }

    public void setFecha_activacion(LocalDateTime fecha_activacion) {
        this.fecha_activacion = fecha_activacion;
    }
    private String email;

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCodigo_activacion() {
        return codigo_activacion;
    }

    public void setCodigo_activacion(String codigo_activacion) {
        this.codigo_activacion = codigo_activacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
