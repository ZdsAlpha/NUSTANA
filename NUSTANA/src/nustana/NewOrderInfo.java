/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

/**
 *
 * @author saifu
 */
public class NewOrderInfo {
     private static String profileId;
    private static String shopId;
    private static String itemId;
    private static int quantity;
    private static String status;
    private static String phoneNumber;
    private static String address;
    private static String comments;
    private static String category;
    
    public static String getCategory(){
        return category;
    }
     
     public static String getProfileId(){
        return profileId;
    }
    public static String getShopId(){
        return shopId;
    }
    public static String getItemId(){
        return itemId;
    }
    public static int getQuantity(){
        return quantity;
    }
    public static String getStatus(){
        return status;
    }
    public static String getPhoneNumber(){
        return phoneNumber;
    }
    public static String getAddress(){
        return address;
    }
    public static String getComments(){
        return comments;
    }
    public static void setCategory(String s){
        category=s;
    }
    public static void setProfileId(String id){
        profileId = id;
    }
    public static void setShopId(String id){
        shopId = id;
    }
    public static void setItemId(String id){
        itemId = id;
    }
    public static void setQuantity(int quantity1){
        quantity = quantity1;
    }
    public static void setStatus(String status1){
        status = status1;
    }
    public static void setPhoneNumber(String phoneNumber1){
        phoneNumber = phoneNumber1;
    }
    public static void setAddress(String address1){
        address = address1;
    }
}
