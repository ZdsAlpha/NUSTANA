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
 * @author saifu
 */
public class Order {
    public static final String TABLE = "Orders";
    private String orderId;
    private String profileId;
    private String shopId;
    private String itemId;
    private int quantity;
    private String status;
    private String phoneNumber;
    private String address;
    private String comments;
    public Order(String orderId){
        this.orderId = orderId;
    }
    public Order(String orderId,String profileId,String shopId,String itemId,int quanitity,String status,String phoneNumber,String address,String comments){
        this(orderId);
        this.profileId = profileId;
        this.shopId = shopId;
        this.itemId = itemId;
        this.quantity = quanitity;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.comments = comments;
    }
    public Order(JSONObject obj){
        this.orderId = obj.getString("objectId");
        this.profileId = obj.getString("ownerId");
        this.shopId = obj.getString("shopId");
        this.itemId = obj.getString("itemId");
        this.quantity = obj.getInt("quantity");
        this.status = obj.getString("status");
        this.phoneNumber = obj.getString("phoneNumber");
        this.address = obj.getString("address");
        this.comments = obj.getString("comments");
    }
    public String getOrderId(){
        return orderId;
    }
    public String getProfileId(){
        return profileId;
    }
    public String getShopId(){
        return shopId;
    }
    public String getItemId(){
        return itemId;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getStatus(){
        return status;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getAddress(){
        return address;
    }
    public String getComments(){
        return comments;
    }
    public void setOrderId(String id){
        this.orderId = id;
    }
    public void setProfileId(String id){
        this.profileId = id;
    }
    public void setShopId(String id){
        this.shopId = id;
    }
    public void setItemId(String id){
        this.itemId = id;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setComments(String comments){
        this.comments = comments;
    }
    public ShopItem getShopItem() throws IOException , BackendlessException{
        return ShopItem.Fetch(itemId);
    }
    public ShopInfo getShopInfo() throws IOException , BackendlessException{
        return ShopInfo.Fetch(shopId);
    }
    public ProfileInfo getProfileInfo() throws IOException , BackendlessException{
        return ProfileInfo.Fetch(profileId);
    }
    public void Save() throws IOException, BackendlessException{
        JSONObject obj = new JSONObject();
        obj.put("shopId", shopId);
        obj.put("itemId",itemId);
        obj.put("qunatity", quantity);
        obj.put("status", status);
        obj.put("phoneNumber",phoneNumber);
        obj.put("address",address);
        obj.put("comments",comments);
        NUSTANA.getClient().UpdateObject(TABLE, obj, orderId);
    }
    public void Load() throws IOException, BackendlessException{
        JSONObject obj = NUSTANA.getClient().GetObject(TABLE, shopId);
        shopId = obj.getString("objectId");
        profileId = obj.getString("ownerId");
        shopId = obj.getString("shopId");
        itemId = obj.getString("itemId");
        quantity = obj.getInt("address");
        status = obj.getString("status");
        phoneNumber = obj.getString("phoneNumber");
        address = obj.getString("address");
        comments = obj.getString("comments");
    }
    @Override
    public String toString() {
        return orderId;
    }
    public static Order Create(String shopId,String itemId,int quantity,String status,String phoneNumber,String address,String comments) throws IOException,BackendlessException{
        JSONObject obj = new JSONObject();
        obj.put("shopId", shopId);
        obj.put("itemId", itemId);
        obj.put("quantity",quantity);
        obj.put("status",status);
        obj.put("phoneNumber",phoneNumber);
        obj.put("address",address);
        obj.put("comments",comments);
        return new Order(NUSTANA.getClient().CreateObject(TABLE, obj));
    }
    public static Order Fetch(String orderId) throws IOException,BackendlessException{
        return new Order(NUSTANA.getClient().GetObject(TABLE, orderId));
    }
    private static Order[] ProcessOrders(JSONArray objects){
        Order[] items = new Order[objects.length()];
        for(int i = 0; i < objects.length();i++){
            JSONObject obj = objects.getJSONObject(i);
            items[i] = new Order(obj);
        }
        return items;
    }
    public static Order[] GetOrders() throws IOException, BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE);
        return ProcessOrders(objects);
    }
    public static Order[] GetOrders(String profileId) throws IOException , BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE, "profileId='" + profileId + "'");
        return ProcessOrders(objects);
    }
    public static Order[] GetShopOrders(String shopId) throws IOException , BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE, "shopId='" + shopId + "'");
        return ProcessOrders(objects);
    }
}
