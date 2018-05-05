package backendless;

import org.json.*;
import java.net.*;
import java.io.*;
/**
 *
 * @author Abdul Rahman
 */
public class BackendlessClient {
    public static final String SERVER = "https://api.backendless.com/";
    public static final String VERSION = "v4.0";
    
    private final String applicationId;
    private final String secretKey;
    private String userToken=null;
    //Constructor
    public BackendlessClient(String applicationId,String secretKey) {
        this.applicationId = applicationId;
        this.secretKey = secretKey;
    }
    public BackendlessClient(String applicationId,String secretKey,String userToken){
        this.applicationId =applicationId;
        this.secretKey = secretKey;
        this.userToken = userToken;
    }
    
    public String getUserToken(){
        return userToken;
    }
    public void setUserToken(String token){
        userToken = token;
    }
    
    public synchronized HttpURLConnection CreateConnection(String method,String parameters,byte[] content,String contentType) throws IOException{
        URL url = new URL(SERVER+applicationId+"/"+secretKey+"/"+parameters);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", contentType);
        if(userToken!=null) connection.setRequestProperty("user-token", userToken);
        if(content != null){
            connection.setDoOutput(true);
            DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
            stream.write(content);
            stream.flush();
            stream.close();
        }
        return connection;
    }
    public synchronized HttpURLConnection CreateConnection(String method,String parameters,byte[] data) throws IOException{
        return CreateConnection(method, parameters,data,"application/json");
    }
    public synchronized HttpURLConnection CreateConnection(String method,String parameters) throws IOException{
        return CreateConnection(method, parameters,null);
    }
    
    public synchronized String CreateRequest(String method,String parameters,byte[] content,String contentType) throws IOException{
        HttpURLConnection connection = CreateConnection(method, parameters, content, contentType);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        int status = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer output = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            output.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return output.toString();
    }
    public synchronized String CreateRequest(String method,String parameters,byte[] content) throws IOException{
        return CreateRequest(method,parameters,content,"application/json");
    }
    public synchronized String CreateRequest(String method,String parameters,JSONObject content) throws IOException{
        if(content==null){
            return CreateRequest(method, parameters,(byte[])null);
        }else{
            return CreateRequest(method, parameters,content.toString().getBytes());
        }
    }
    public synchronized String CreateRequest(String method,String parameters) throws IOException{
        return CreateRequest(method,parameters,(byte[])null);
    }
    
    public synchronized JSONObject Register(JSONObject object)throws IOException,BackendlessException {
        JSONObject response = new JSONObject(CreateRequest("POST", "users/register",object));
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return response;
    }
    public synchronized JSONObject Register(String email,String password,JSONObject properties) throws IOException,BackendlessException{
        if(properties==null) properties = new JSONObject();
        properties.put("email", email);
        properties.put("password", password);
        return Register(properties);
    }
    
    public synchronized JSONObject Login(String email,String password) throws IOException, BackendlessException{
        JSONObject object = new JSONObject();
        object.put("login", email);
        object.put("password", password);
        JSONObject response = new JSONObject(CreateRequest("POST", "users/login",object));
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        userToken = response.getString("user-token");
        return response;
    }
    
    public synchronized JSONObject UpdateUser(String userId,JSONObject object) throws IOException, BackendlessException{
        JSONObject response = new JSONObject(CreateRequest("PUT", "users/"+userId, object));
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return response;
    }
    public synchronized JSONObject UpdateUser(String userId,String email,String password,JSONObject properties) throws IOException, BackendlessException{
        if(properties==null) properties = new JSONObject();
        properties.put("email",email);
        properties.put("password",password);
        return UpdateUser(userId, properties);
    }
    
    public synchronized JSONObject Logout() throws IOException, BackendlessException{
        try{
            JSONObject response = new JSONObject(CreateRequest("GET", "users/logout"));
            BackendlessException exception = BackendlessException.getException(response);
            if(exception != null) throw exception;
            return response;
        }catch(JSONException ex){
            return null;
        }
    }
    
    public synchronized JSONObject ResetPassword(String email) throws IOException , BackendlessException{
        try{
            JSONObject response = new JSONObject(CreateRequest("GET", "users/restorepassword/"+email));
            BackendlessException exception = BackendlessException.getException(response);
            if(exception != null) throw exception;
            return response;
        }catch(JSONException ex){
            return null;
        }
    }
    
    public synchronized JSONObject CreateObject(String table,JSONObject object)throws IOException , BackendlessException {
        JSONObject response = new JSONObject(CreateRequest("POST", "data/"+table, object));
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return response;
    }
    public synchronized JSONObject UpdateObject(String table,JSONObject object,String objectId) throws IOException , BackendlessException {
        JSONObject response = new JSONObject(CreateRequest("PUT", "data/"+table+"/"+objectId, object));
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return response;
    }
    public synchronized JSONObject GetObject(String table,String objectId) throws IOException , BackendlessException{
        JSONObject response = new JSONObject(CreateRequest("GET","data/"+table+"/"+objectId));
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return response;
    }
    public synchronized JSONObject DeleteObject(String table,String objectId) throws IOException , BackendlessException{
        JSONObject response = new JSONObject(CreateRequest("DELETE","data/"+table+"/"+objectId));
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return response;
    }
    
    public synchronized JSONArray GetObjects(String table) throws IOException , BackendlessException{
        String response = CreateRequest("GET","data/"+table);
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return new JSONArray(response);
    }
    public synchronized JSONArray GetObjects(String table,int offset) throws IOException , BackendlessException{
        String response = CreateRequest("GET","data/"+table+"?offset"+offset);
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return new JSONArray(response);
    }
    public synchronized JSONArray GetObjects(String table,int offset,int limit) throws IOException , BackendlessException{
        String response = CreateRequest("GET","data/"+table+"?offset"+offset+",pageSize="+limit);
        BackendlessException exception = BackendlessException.getException(response);
        if(exception != null) throw exception;
        return new JSONArray(response);
    }
}
