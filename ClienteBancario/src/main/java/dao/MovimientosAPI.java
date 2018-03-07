/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movimiento;
import utils.Constantes;

/**
 *
 * @author Eduardo erasto
 */
public class MovimientosAPI
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
    
    public int crearMovimientoDAO(Movimiento mo){//api
        
        Movimiento movimiento = null;
        int aux = 0;
        
        try {
            //GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);
            ObjectMapper mapper = new ObjectMapper();
            GenericData data = new GenericData();
            data.put("crearMovimiento", mapper.writeValueAsString(mo));
            data.put("op", "crearMovimiento");

            HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
                   
            HttpResponse response = requestGoogle.execute();
            movimiento = mapper.readValue(response.getContent(), Movimiento.class);//pasar esto a entero

            /////////////
            if(movimiento != null){
                aux = 1;
            }else{
                aux = -1;
            }
            
        } catch (HttpResponseException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CuentasAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aux;
    }
}
