/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

/**
 *
 * @author Abdul Rahman
 */
public class FileSystem {
    public static void SaveBytes(String file,byte[] contents) throws IOException{
        Files.write(Paths.get(file),contents);
    }
    public static byte[] LoadBytes(String file) throws IOException{
        return Files.readAllBytes(Paths.get(file));
    }
    public static void SaveString(String file,String contents) throws IOException{
        SaveBytes(file, contents.getBytes());
    }
    public static String LoadString(String file) throws IOException{
        return new String(LoadBytes(file));
    }
    public static void SaveObject(String file,JSONObject object) throws IOException{
        SaveString(file,object.toString());
    }
    public static JSONObject LoadObject(String file) throws IOException{
        return new JSONObject(LoadString(file));
    }
}
