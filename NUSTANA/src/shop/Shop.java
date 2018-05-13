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
    public static JSONArray retrieveShops() throws IOException, BackendlessException{
        return NUSTANA.getClient().GetObjects("Shops", "ownerId='"+Profile.getUserID()+"'");
    }
}
