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
public class Profile {
    public static final String CONFIGURATION_FILE_PATH = "profileConfig.json";
    private static ProfileInfo profile;
    private static String password;
    public static ProfileInfo Register(String name,String email,String phoneNumber,String password) throws IOException , BackendlessException{
        return ProfileInfo.Register(email, name, phoneNumber, password);
    }
    public static ProfileInfo Login(String userEmail,String userPassword) throws IOException , BackendlessException{
        profile = ProfileInfo.Login(userEmail, userPassword);
        password = userPassword;
        return profile;
    }
    public static ProfileInfo Update(JSONObject properties)throws IOException,BackendlessException{
        profile = new ProfileInfo(NUSTANA.getClient().UpdateUser(profile.getProfileId(), properties));
        return profile;
    }
    public static ProfileInfo Update(String name,String phoneNumber,String password) throws IOException, BackendlessException{
        JSONObject properties = new JSONObject();
        properties.put("name", name);
        properties.put("phoneNumber",phoneNumber);
        properties.put("password", password);
        return Update(properties);
    }
    public static JSONObject Reset(String email) throws IOException , BackendlessException{
        return NUSTANA.getClient().ResetPassword(email);
    }
    public static JSONObject Logout() throws IOException , BackendlessException{
        JSONObject obj = NUSTANA.getClient().Logout();
        profile = null;
        password = null;
        return obj;
    }
    public static ProfileInfo getProfile(){
        return profile;
    }
    public static boolean isLoggedin(){
        return profile!=null;
    }
    public static String getPassword(){
        return password;
    } 
    public static String getName(){
        return profile.getName();
    }
    public static String getEmail(){
        return profile.getEmail();
    }
    public static String getPhoneNumber(){
        return profile.getPhoneNumber();
    }
    public static String getProfileId(){
        return profile.getProfileId();
    }
    public static boolean isDisabled(){
        return profile.getProfileStatus().equals("DISABLED");
    }
    public static boolean isConfirmed(){
        return profile.getProfileStatus().equals("EMAIL_CONFIRMATION_PENDING");
    }
}
