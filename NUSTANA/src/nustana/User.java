/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import backendless.BackendlessException;
import java.io.IOException;
import org.json.JSONObject;
import tools.ExceptionHandling;
import tools.UI;

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
    private static String password;
    private static String userID;
    public static JSONObject Register(String name,String email,String phoneNumber,String password) throws IOException , BackendlessException{
        JSONObject properties = new JSONObject();
        properties.put("name", name);
        properties.put("phoneNumber",phoneNumber);
        return NUSTANA.getClient().Register(email, password, properties);
    }
    public static JSONObject Login(String userEmail,String userPassword) throws IOException , BackendlessException{
        JSONObject properties = NUSTANA.getClient().Login(userEmail, userPassword);
        isLoggedin = true;
        name = properties.getString("name");
        email = properties.getString("email");
        phoneNumber = properties.getString("phoneNumber");
        userStatus = properties.getString("userStatus");
        password = properties.getString("password");
        userID=properties.getString("objectId");
        return properties;
    }
    
    public static JSONObject UpdateUser(String userName, String userEmail, String userPhoneNumber,  String userPassword )throws IOException,BackendlessException{
        JSONObject properties = new JSONObject();
        properties.put("name", userName);
        properties.put("phoneNumber",  userPhoneNumber);
       
      
        JSONObject obj =NUSTANA.getClient().UpdateUser(userID, userEmail, userPassword, properties);
        name = properties.getString("name");
        email = properties.getString("email");
        phoneNumber = properties.getString("phoneNumber");
        password = properties.getString("password");
        return obj;
    }
    public static JSONObject Reset(String email) throws IOException , BackendlessException{
        return NUSTANA.getClient().ResetPassword(email);
    }
    
    public static String getPassword(){
        return password;
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
