package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
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
import config.Configuration;

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

    //public GenericData data = new GenericData();
    GenericUrl url = new GenericUrl("http://localhost:8080/ApiServidorJava/rest/apiAlumnos");
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Alumno> getAllAlumnosJDBC() throws IOException {
  
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        List<Alumno> datos = objectMapper.readValue(requestGoogle.execute().parseAsString(),
                             objectMapper.getTypeFactory().constructCollectionType(List.class,Alumno.class));
        
        return datos;
    }
    
    public String insertAlumnoJDBC(Alumno a) throws IOException {
        
        url.set("alumno",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        //requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        return (String)gj.get("mensaje");
        
        /*
        data.put("nombre",a.getNombre());
        data.put("fecha_nacimiento",a.getFecha_nacimiento());
        data.put("mayor",a.getMayor_edad());
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new UrlEncodedContent(data));
        int datos = objectMapper.readValue(
        requestGoogle.execute().parseAsString(),
        objectMapper.getTypeFactory().constructCollectionType(List.class,Alumno.class));
        
        return datos;
        */
    }
    public String updateAlumnoJDBC(Alumno a) throws IOException {
        url.set("alumno",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        
        GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        return (String)gj.get("mensaje");
    }
    
    public String deleteAlumnoJDBC(Alumno a) throws IOException {
        url.set("alumno",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);//porque solo la url
        
        GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        return (String)gj.get("mensaje");
    }
}
