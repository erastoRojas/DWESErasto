/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.chatserver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import model.MensajeWS;

/**
 *
 * @author DAW
 */
@ServerEndpoint("/endpoint/{user}/{pass}")
public class ServerEndPoint {
    
    @OnOpen
    public void onOpen(Session session,
        @PathParam("user") String user,     //recoge del cliente
        @PathParam("pass") String pass) {

        session.getUserProperties().put("user",user);   //crea variables //get user propierties se usa para utilizarlos en los metodos de abajo
        session.getUserProperties().put("pass",pass);
        
        if (user.equals("google")) {    //busca en la base de datos
            session.getUserProperties().put("login",
              "si"); 
        } else {
            session.getUserProperties().put("login",//o lo inserta en la base de datos?
              "no");
        }

        try {
            
            if (session.getUserProperties().get("login") != "si"){
                session.close();   
            }else{
                //manda mensaje al cliente
            }
              
          } catch (IOException ex) {
              Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
   
    @OnMessage//aqui recibe los mensajes del cliente
    public void echoText(String mensaje, Session sessionQueManda) throws IOException {
        //hay que parsear el string que recibo a objeto para trabajar con el
        
        if (sessionQueManda.getUserProperties().get("login").equals("si")) {
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MensajeWS meta = mapper.readValue(mensaje,
                    new TypeReference<MensajeWS>() {
            });
            
                //Object to JSON in String
                String jsonInString = mapper.writeValueAsString(meta);
                
                for (Session sesionesMandar : sessionQueManda.getOpenSessions()) {

                sesionesMandar.getBasicRemote().sendText(jsonInString);   
                
            }
        }
    }
         
   

//    @WebSocketMessage
//    public void echoBinary(ByteBuffer data, Session session) throws IOException {
//        System.out.println("echoBinary: " + data);
//        StringBuilder builder = new StringBuilder();
//        for (byte b : data.array()) {
//            builder.append(b);
//        }
//        System.out.println(builder);
//        session.getRemote().sendBytes(data);
//    }
}
