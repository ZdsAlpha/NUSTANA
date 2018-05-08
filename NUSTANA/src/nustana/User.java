/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import backendless.BackendlessException;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author Abdul Rahman
 */
public class User {
    private static boolean isLoggedin = false;
    private static String name;
    private static String email;
    private static String phoneNumber;
    private static String userStatus;
    public static JSONObject Register(String name,String email,String phoneNumber,String password) throws IOException , BackendlessException{
        JSONObject properties = new JSONObject();
        properties.put("name", name);
        properties.put("phoneNumber",phoneNumber);
        return NUSTANA.getClient().Register(email, password, properties);
    }
    public static JSONObject Login(String email,String password) throws IOException , BackendlessException{
        JSONObject properties = NUSTANA.getClient().Login(email, password);
        isLoggedin = true;
        name = properties.getString("name");
        email = properties.getString("email");
        phoneNumber = properties.getString("phoneNumber");
        userStatus = properties.getString("userStatus");
        return properties;
    }
    public static JSONObject Reset(String email) throws IOException , BackendlessException{
        return NUSTANA.getClient().ResetPassword(email);
    }
    public static String getName(){
        return name;
    }
    public static String getEmail(){
        return email;
    }
    public static String getPhoneNumber(){
        return phoneNumber;
    }
    public static boolean isDisabled(){
        return userStatus.equals("DISABLED");
    }
    public static boolean isConfirmed(){
        return userStatus.equals("EMAIL_CONFIRMATION_PENDING");
    }
}
