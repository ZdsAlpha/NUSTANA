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
public class ShopInfo {
    public static final String TABLE = "Shops";
    private String shopId;
    private String profileId;
    private String name;
    private String phoneNumber;
    private String address;
    private String description;
    public ShopInfo(String shopId){
        this.shopId = shopId;
    }
    public ShopInfo(String shopId,String profileId,String name,String phoneNumber,String address,String description){
        this(shopId);
        this.profileId = profileId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.description = description;
    }
    public ShopInfo(JSONObject obj){
        shopId = obj.getString("objectId");
        profileId = obj.getString("ownerId");
        name = obj.getString("name");
        phoneNumber = obj.getString("phoneNumber");
        address = obj.getString("address");
        description = obj.getString("desccription");
    }
    public String getShopId(){
        return shopId;
    }
    public String getProfileId(){
        return profileId;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getAddress(){
        return address;
    }
    public String getDescription(){
        return description;
    }
    public void setShopId(String id){
        this.shopId = id;
    }
    public void setProfileId(String id){
        this.profileId = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setDescrption(String description){
        this.description = description;
    }
    public void Save() throws IOException, BackendlessException{
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("phoneNumber", phoneNumber);
        obj.put("address", address);
        obj.put("description",description);
        NUSTANA.getClient().UpdateObject(TABLE, obj, shopId);
    }
    public void Load() throws IOException, BackendlessException{
        JSONObject obj = NUSTANA.getClient().GetObject(TABLE, shopId);
        shopId = obj.getString("objectId");
        profileId = obj.getString("ownerId");
        name = obj.getString("name");
        phoneNumber = obj.getString("phoneNumber");
        address = obj.getString("address");
        description = obj.getString("desccription");
    }
    @Override
    public String toString() {
        return name;
    }
    private static ShopInfo[] ProcessItems(JSONArray objects){
        ShopInfo[] items = new ShopInfo[objects.length()];
        for(int i = 0; i < objects.length();i++){
            JSONObject obj = objects.getJSONObject(i);
            items[i] = new ShopInfo(obj);
        }
        return items;
    }
    public static ShopInfo[] GetItems() throws IOException, BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE);
        return ProcessItems(objects);
    }
    public static ShopInfo[] GetItems(String profileId) throws IOException , BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE, "profileId='" + profileId + "'");
        return ProcessItems(objects);
    }
}