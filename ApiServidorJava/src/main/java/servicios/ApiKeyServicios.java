/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ApiKeyDAO;
import java.util.List;
import model.ApiKey;

/**
 *
 * @author Eduardo DAW
 */
public class ApiKeyServicios{
/*
    public List<ApiKey> selectApiKey(ApiKey k){
        
        ApiKeyDAO dao = new ApiKeyDAO();
        return dao.selectApiKeyJDBC(k);
    }*/
     public ApiKey selectApiKey(ApiKey k){
        
        ApiKeyDAO dao = new ApiKeyDAO();
        return dao.selectApiKeyJDBC(k);
    }
}
