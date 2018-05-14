/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import backendless.BackendlessException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import nustana.*;

/**
 *
 * @author Abdul Rahman
 */
public class Shop {
    private static String shopId;
    private static String name;
    private static String phoneNumber;
    private static String address;
    private static String description;
    public static JSONArray RetrieveShops() throws IOException, BackendlessException{
        return NUSTANA.getClient().GetObjects("Shops", "ownerId='"+Profile.getProfileId()+"'");
    }
    public static void Login() throws IOException, BackendlessException {
        JSONArray shops = RetrieveShops();
        if(shops.length()==0) throw new IndexOutOfBoundsException("No shop found!");
        JSONObject shop = shops.getJSONObject(0);
        shopId = shop.getString("objectId");
        name = shop.getString("name");
        phoneNumber = shop.getString("phoneNumber");
        address = shop.getString("address");
        description = shop.getString("description");
    }
    public static String getShopId(){
        return shopId;
    }
    public static String getName(){
        return name;
    }
    public static String getPhoneNumber(){
        return phoneNumber;
    }
    public static String getAddress(){
        return address;
    }
    public static String getDescription(){
        return description;
    }
    
}
