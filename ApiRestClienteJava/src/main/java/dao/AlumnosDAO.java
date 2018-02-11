package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.lang.reflect.Type;
import model.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Constantes;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author Erasto Rojas Sánchez
 */
public class AlumnosDAO {

    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    JsonFactory JSON_FACTORY = new JacksonFactory();
    
    HttpRequestFactory requestFactory
          = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
              }
          });
    GenericUrl url = new GenericUrl("http://localhost:8282/ApiServidor/rest/apiAlumnos");
    
    
    public GenericData data = new GenericData();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date today = Calendar.getInstance().getTime();
    public String reportDate = df.format(today);
    
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Alumno> getAllAlumnosJDBC() throws IOException {
             
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        List<Alumno> datos = objectMapper.readValue(
        requestGoogle.execute().parseAsString(),
        objectMapper.getTypeFactory().constructCollectionType(List.class,Alumno.class));
        
        return datos;
    }
    
    public int insertAlumnoJDBC(Alumno a) throws IOException {
        
        data.put("nombre",a.getNombre());
        data.put("fecha_nacimiento",a.getFecha_nacimiento());
        data.put("mayor",a.getMayor_edad());
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new UrlEncodedContent(data));
        int datos = objectMapper.readValue(
        requestGoogle.execute().parseAsString(),
        objectMapper.getTypeFactory().constructCollectionType(List.class,Alumno.class));
        
        return datos;
    }
    public int updateAlumnoJDBC(Alumno a) {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE ALUMNOS SET NOMBRE = ?,FECHA_NACIMIENTO = ?, MAYOR_EDAD = ? WHERE ID = ?");
 
            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());
            stmt.setLong(4,a.getId());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int deleteAlumnoJDBC(Alumno a) {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM ALUMNOS WHERE ID =" + a.getId());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
}
