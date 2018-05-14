/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import org.json.JSONObject;

/**
 *
 * @author saifu
 */
public class Order {
    private String orderStatus;
    private String itemName;
    private String itemPrice;
    
    public Order(String name, String price){
        orderStatus="pending";
        itemName=name;
        itemPrice=price;
    }
    
    public JSONObject newOrder(){
        JSONObject order = new JSONObject();
        order.put("clientName", Profile.getName());
        order.put("email", Profile.getEmail());
        order.put("primaryPhoneNumber", Profile.getPhoneNumber());
        order.put("userID", Profile.getUserID());
        order.put("itemName",itemName);
        return order;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public String getItemPrice(){
        return itemPrice;
    }
    
}
