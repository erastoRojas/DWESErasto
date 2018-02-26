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
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Asignatura;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
/**
 *
 * @author oscar
 */
public class AsignaturasDAO {
    
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
    GenericUrl url = new GenericUrl("http://localhost:8282/ApiServidorJava/rest/apiAsignaturas");
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Asignatura> getAllAsignaturasDBUils() throws IOException {
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        List<Asignatura> datos = objectMapper.readValue(requestGoogle.execute().parseAsString(),
                                 objectMapper.getTypeFactory().constructCollectionType(List.class,Asignatura.class));
        
        return datos;
    }
    
    public String insertAsignaturasDBUtils(Asignatura a) throws IOException{
        url.set("asignatura",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        
        return requestGoogle.execute().parseAsString();
    }
    
    public String updateAsignaturasDBUtils(Asignatura a) throws IOException{
        url.set("asignatura",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        
        return requestGoogle.execute().parseAsString();
    }
    
    public String deleteAsignaturasDBUtils(Asignatura a) throws IOException{
        url.set("asignatura",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        
        return requestGoogle.execute().parseAsString();
    }
}