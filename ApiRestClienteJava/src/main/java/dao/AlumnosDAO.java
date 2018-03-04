package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public GenericData data = new GenericData();
    GenericUrl url = new GenericUrl("http://localhost:8080/ApiServidorJava/rest/apiAlumnos");
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Alumno> getAllAlumnosJDBC() throws IOException {
        
        List<Alumno> alumnos = null;
        
        try{
   
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);          
        requestGoogle.getHeaders().set("API_KEY", "sadfsdf");
        
        HttpResponse response = requestGoogle.execute();
        ObjectMapper mapper = new ObjectMapper();
        alumnos = mapper.readValue(response.getContent(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));

        } catch (HttpResponseException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return alumnos;
        
    }
    
    public String insertAlumnoJDBC(Alumno a) throws IOException {
        
        Alumno alumnoResponse = null;
        
        try {
        
        ObjectMapper mapper = new ObjectMapper();
        GenericData data = new GenericData();
        data.put("alumno", mapper.writeValueAsString(a));
        
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url,new UrlEncodedContent(data));          
        requestGoogle.getHeaders().set("API_KEY", "sadfsdf");
        
        HttpResponse response = requestGoogle.execute();
        
        alumnoResponse = mapper.readValue(response.getContent(), Alumno.class);
        
        } catch (HttpResponseException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnoResponse;
        
        //GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        //return (String)gj.get("mensaje");
        
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
        return (String) gj.get("mensaje");
       
    }
    
    public String deleteAlumnoJDBC(Alumno a) throws IOException {
        url.set("alumno",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);//porque solo la url
        
        GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        return (String)gj.get("mensaje");
    }
    
    //RECIBIR DOS OBJETOS DIFERENTES
    //Y PARSEARLOS A UNA LISTA
    public List<List> getListas() throws IOException{
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("API_KEY", "sdfsdf");
        Type type = new TypeToken<List<List>>() {}.getType();
        List<List> lista = (List) requestGoogle.execute().parseAs(type);
        
        //PARA SEPARAR EL CONTENIDO DE ESTA LISTA
        HttpResponse response = requestGoogle.execute();
        
        List <Alumno> a = 
                objectMapper.readValue(response.parseAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));
        
        
        return lista;
    }
    
    
}
