/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import backendless.BackendlessException;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Abdul Rahman
 */
public class ShopItem {
    public static final String TABLE = "Items";
    private String itemId;
    private String profileId;
    private String shopId;
    private String name;
    private int price;
    private String category;
    private String description;
    public ShopItem(String itemId){
        this.itemId = itemId;
    }
    public ShopItem(String itemId,String shopId){
        this(itemId);
        this.shopId = shopId;
    }
    public ShopItem(String itemId,String shopId,String profileId,String name,int price,String descritpion){
        this(itemId,shopId);
        this.profileId = profileId;
        this.name = name;
        this.price = price;
        this.description = descritpion;
    }
    public ShopItem(JSONObject obj){
        itemId = obj.getString("objectId");
        profileId = obj.getString("ownerId");
        shopId = obj.getString("shopId");
        name = obj.getString("name");
        price = obj.getInt("price");
        category = obj.getString("category");
        description = obj.getString("description");
    }
    public String getItemId(){
        return itemId;
    }
    public String getProfileId(){
        return profileId;
    }
    public String getShopId(){
        return shopId;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public String getCategory(){
        return category;
    }
    public String getDescription(){
        return description;
    }
    public void setItemId(String id){
        this.itemId = id;
    }
    public void setProfileId(String id){
        profileId = id;
    }
    public void setShopId(String id){
        this.shopId = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public ShopInfo getShopInfo() throws IOException , BackendlessException{
        return ShopInfo.Fetch(shopId);
    }
    public ProfileInfo getProfileInfo() throws IOException , BackendlessException{
        return ProfileInfo.Fetch(profileId);
    }
    public Order[] getOrders() throws IOException , BackendlessException{
        return Order.GetOrdersByItem(itemId);
    }
    public void Save() throws IOException, BackendlessException{
        JSONObject obj = new JSONObject();
        obj.put("shopId", shopId);
        obj.put("name", name);
        obj.put("price", price);
        obj.put("category",category);
        obj.put("description",description);
        NUSTANA.getClient().UpdateObject(TABLE, obj, itemId);
    }
    public void Load() throws IOException, BackendlessException{
        JSONObject obj = NUSTANA.getClient().GetObject(TABLE, itemId);
        shopId = obj.getString("shopId");
        profileId = obj.getString("ownerId");
        itemId = obj.getString("itemId");
        name = obj.getString("name");
        price = obj.getInt("price");
        category = obj.getString("category");
        description = obj.getString("description");
    }
    public void Delete() throws IOException, BackendlessException{
        NUSTANA.getClient().DeleteObject(TABLE, itemId);
    }
    @Override
    public String toString() {
        return name;
    }
    public static ShopItem Create(String shopId,String name,int price,String category,String description) throws IOException , BackendlessException{
        JSONObject obj = new JSONObject();
        obj.put("shopId", shopId);
        obj.put("name",name);
        obj.put("price",price);
        obj.put("category",category);
        obj.put("description",description);
        return new ShopItem(NUSTANA.getClient().CreateObject(TABLE, obj));
    }
    public static ShopItem Fetch(String itemId) throws IOException,BackendlessException{
        return new ShopItem(NUSTANA.getClient().GetObject(TABLE, itemId));
    }
    private static ShopItem[] ProcessItems(JSONArray objects){
        ShopItem[] items = new ShopItem[objects.length()];
        for(int i = 0; i < objects.length();i++){
            JSONObject obj = objects.getJSONObject(i);
            items[i] = new ShopItem(obj);
        }
        return items;
    }
    public static ShopItem[] GetItems() throws IOException, BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE);
        return ProcessItems(objects);
    }
    public static ShopItem[] GetItems(String shopId) throws IOException , BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE, "shopId='" + shopId + "'");
        return ProcessItems(objects);
    }
    public static ShopItem[] GetItems(String shopId,String category) throws IOException , BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE,"shopId='" + shopId + "' and category='" + category + "'");
        return ProcessItems(objects);
    }
    public static ShopItem[] GetItemsByCategory(String category) throws IOException , BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE, "category='" + category + "'");
        return ProcessItems(objects);
    }
    public static ShopItem[] GetItemsWhere(String where) throws IOException , BackendlessException{
        JSONArray objects = NUSTANA.getClient().GetObjects(TABLE, where);
        return ProcessItems(objects);
    }
}
