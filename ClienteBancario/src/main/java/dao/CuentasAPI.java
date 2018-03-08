/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cuenta;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Constantes;



/**
 *
 * @author erasto
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
    GenericUrl url = new GenericUrl(Constantes.INGRESOS_Y_REINTEGROS_REST);
    ObjectMapper objectMapper = new ObjectMapper();
    
public Cuenta getCuentaDAO(Cuenta cu) {
    
        Cuenta cuenta = null;
        try{
        
            ObjectMapper mapper = new ObjectMapper();
            //GenericData data = new GenericData();
            //data.put("getCuenta", mapper.writeValueAsString(cu));
            //data.put("op", "getCuenta");
            url.set("getCuenta",objectMapper.writeValueAsString(cu));
            url.set("op", "getCuenta");
            
            HttpRequest requestGoogle = requestFactory.buildGetRequest(url);          
            //requestGoogle.getHeaders().set("API_KEY", "sadfsdf");
            HttpResponse response = requestGoogle.execute();

            cuenta = mapper.readValue(response.getContent(), Cuenta.class);

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
            
            ObjectMapper mapper = new ObjectMapper();
            //GenericData data = new GenericData();
            //data.put("getSaldo", mapper.writeValueAsString(cu));
            //data.put("op", "getSaldo");
            url.set("getSaldo",objectMapper.writeValueAsString(cu));
            url.set("op", "getSaldo");

            HttpRequest requestGoogle = requestFactory.buildGetRequest(url);          
            //requestGoogle.getHeaders().set("API_KEY", "sadfsdf");
            HttpResponse response = requestGoogle.execute();

            cuenta = mapper.readValue(response.getContent(), Cuenta.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cuenta;
    }
    
    public int updateCuentaDAO(Cuenta cu){//api
        
        //Cuenta cuenta = null;
        int aux = 0;
        
        try {
            //GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);
            ObjectMapper mapper = new ObjectMapper();
            //GenericData data = new GenericData();
            //data.put("updateCuenta", mapper.writeValueAsString(cu));
            //data.put("op", "updateCuenta");
            
            url.set("updateCuenta",objectMapper.writeValueAsString(cu));
            url.set("op", "updateCuenta");
            
            
            HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), cu));
                   
            //HttpResponse response = requestGoogle.execute();
            //cuenta = mapper.readValue(response.getContent(), Cuenta.class);//pasar esto a entero
            
            GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
            String hola =  (String)gj.toString();
            
            //////////////*
            
            aux = Integer.parseInt(hola);
            
            
        } catch (HttpResponseException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aux;
    }
    
}
