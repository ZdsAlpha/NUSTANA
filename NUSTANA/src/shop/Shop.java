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
    private static ShopInfo shop;
    public static ShopInfo[] RetrieveShops() throws IOException, BackendlessException{
        return ShopInfo.GetShops(Profile.getProfileId());
    }
    public static void Login() throws IOException, BackendlessException {
        ShopInfo[] shops = RetrieveShops();
        if(shops.length==0) throw new IndexOutOfBoundsException("No shop found!");
        shop = shops[0];
    }
    public static ShopInfo getShopInfo(){
        return shop;
    }
    public static String getShopId(){
        return shop.getShopId();
    }
    public static String getName(){
        return shop.getName();
    }
    public static String getPhoneNumber(){
        return shop.getPhoneNumber();
    }
    public static String getAddress(){
        return shop.getAddress();
    }
    public static String getDescription(){
        return shop.getDescription();
    }
}
