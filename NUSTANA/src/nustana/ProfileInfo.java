/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import backendless.BackendlessException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Abdul Rahman
 */
public class ProfileInfo {
    public static final String TABLE = "Users";
    private String profileId;
    private String email;
    private String name;
    private String phoneNumber;
    private String profileStatus;
    public ProfileInfo(String profileId){
        this.profileId = profileId;
    }
    public ProfileInfo(String profileId,String email,String name,String phoneNumber){
        this(profileId);
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public ProfileInfo(JSONObject obj){
        profileId = obj.getString("objectId");
        email = obj.getString("email");
        name = obj.getString("name");
        phoneNumber = obj.getString("phoneNumber");
        profileStatus = obj.getString("userStatus");
    }
    public String getProfileId(){
        return profileId;
    }
    public String getEmail(){
        return email;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getProfileStatus(){
        return profileStatus;
    }
    public void setProfileId(String id){
        this.profileId = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setProfileStatus(String status){
        this.profileStatus = status;
    }
    public ShopInfo[] getShops() throws IOException , BackendlessException{
        return ShopInfo.GetShops(profileId);
    }
    public void Save() throws IOException, BackendlessException{
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("name", name);
        obj.put("phoneNumber", phoneNumber);
        NUSTANA.getClient().UpdateObject(TABLE, obj, profileId);
    }
    public void Load() throws IOException, BackendlessException{
        JSONObject obj = NUSTANA.getClient().GetObject(TABLE, profileId);
        email = obj.getString("email");
        name = obj.getString("name");
        phoneNumber = obj.getString("phoneNumber");
        profileStatus = obj.getString("userStatus");
    }
    public void Delete() throws IOException, BackendlessException{
        NUSTANA.getClient().DeleteObject(TABLE, profileId);
    }
    @Override
    public String toString() {
        return name;
    }
    public static ProfileInfo Register(String email,String name,String phoneNumber,String password) throws IOException , BackendlessException{
        JSONObject properties = new JSONObject();
        properties.put("name", name);
        properties.put("phoneNumber",phoneNumber);
        return new ProfileInfo(NUSTANA.getClient().Register(email, password, properties));
    }
    public static ProfileInfo Login(String email,String password) throws IOException , BackendlessException{
        return new ProfileInfo(NUSTANA.getClient().Login(email, password));
    }
    public static ProfileInfo Fetch(String profileId) throws IOException , BackendlessException{
        return new ProfileInfo(NUSTANA.getClient().GetObject(TABLE, profileId));
    }
    private static ProfileInfo[] ProcessProfiles(JSONArray objects){
        ProfileInfo[] profiles = new ProfileInfo[objects.length()];
        for(int i = 0; i < objects.length();i++){
            JSONObject obj = objects.getJSONObject(i);
            profiles[i] = new ProfileInfo(obj);
        }
        return profiles;
    }
    public static ProfileInfo[] GetProfiles() throws IOException, BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE);
        return ProcessProfiles(objects);
    }
}
