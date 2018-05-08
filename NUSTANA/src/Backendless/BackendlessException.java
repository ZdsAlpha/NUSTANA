/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendless;

import org.json.JSONObject;

/**
 *
 * @author Abdul Rahman
 */
public class BackendlessException extends Exception {
    private int code;
    public BackendlessException(int code,String message){
        super(message);
        this.code = code;
    }
    public int getCode(){
        return code;
    }
    public static BackendlessException getException(JSONObject response){
        try{
            int code = response.getInt("code");
            String message = response.getString("message");
            return new BackendlessException(code, message);
        }catch(Exception ex){
            return null;
        }
    }
    public static BackendlessException getException(String response){
        try{
            return getException(new JSONObject(response));
        }catch(Exception ex){
            return null;
        }
    }
}
