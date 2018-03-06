/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import model.Cuenta;


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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.reflect.TypeToken;



/**
 *
 * @author Eduardo DAW
 */
public class CuentasAPI
{
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
    
public Cuenta getCuentaDAO(Cuenta cu) {
    
        Cuenta cuenta = null;
        try{
   
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);          
        //requestGoogle.getHeaders().set("API_KEY", "sadfsdf");
        
        HttpResponse response = requestGoogle.execute();
        ObjectMapper mapper = new ObjectMapper();
        cuenta = mapper.readValue(response.getContent(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cuenta.class));

        } catch (HttpResponseException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cuenta;
    }
    
    public Cuenta getSaldoDAO(Cuenta cu){//api
        
        Cuenta cuenta = null;
        try {
            //GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);
            ObjectMapper mapper = new ObjectMapper();
            GenericData data = new GenericData();
            data.put("cuentaGetSaldo", mapper.writeValueAsString(cu));

            HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
                   
            HttpResponse response = requestGoogle.execute();
            cuenta = mapper.readValue(response.getContent(), Cuenta.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cuenta;
    }
    
    public Cuenta updateCuentaDAO(Cuenta cu){//api
        
        Cuenta cuenta = null;
        try {
            //GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);
            ObjectMapper mapper = new ObjectMapper();
            GenericData data = new GenericData();
            data.put("cuentaGetSaldo", mapper.writeValueAsString(cu));

            HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
                   
            HttpResponse response = requestGoogle.execute();
            cuenta = mapper.readValue(response.getContent(), Cuenta.class);//pasar esto a booleano

        } catch (HttpResponseException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cuenta;
    }
    
}
