/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import backendless.BackendlessClient;
import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.json.JSONObject;
import tools.FileSystem;

/**
 *
 * @author Abdul Rahman
 */
public class NUSTANA {
    public static final String CONFIGURATION_FILE_PATH = "appConfig.json";
    public static String ApplicationId;
    public static String SecretKey;
    //Application entry point
    public static void main(String[] args) throws Exception {
        initializeLogger();
        initializeUI();
        Logger.Log("Application initialized!");
        if(!loadConfig()) {
            //TODO: Reconfiguration
            JOptionPane.showMessageDialog(null, "Unable to load configuration from file \""+CONFIGURATION_FILE_PATH+"\".","Error!",JOptionPane.ERROR_MESSAGE);
            new Configuration().setVisible(true);
        }
        else{
            Logger.Log("Application configured!");
        }
    }
    //Initialize logger
    public static void initializeLogger(){
        Logger.initialize();
    }
    //Changes UI Theme
    public static void initializeUI(){
        UIManager.put( "control", new Color( 128, 128, 128) );
        UIManager.put( "info", new Color(128,128,128) );
        UIManager.put( "nimbusBase", new Color( 18, 30, 49) );
        UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
        UIManager.put( "nimbusDisabledText", new Color( 128, 128, 128) );
        UIManager.put( "nimbusFocus", new Color(115,164,209) );
        UIManager.put( "nimbusGreen", new Color(176,179,50) );
        UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
        UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
        UIManager.put( "nimbusOrange", new Color(191,98,4) );
        UIManager.put( "nimbusRed", new Color(169,46,34) );
        UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
        UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
        UIManager.put( "text", new Color( 230, 230, 230) );
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex){}
    }
    //Load configuration
    public static boolean loadConfig(){
        try{
            JSONObject config = FileSystem.LoadObject(CONFIGURATION_FILE_PATH);
            ApplicationId = config.getString("applicationId");
            SecretKey = config.getString("secretKey");
            return true;
        }catch(Exception ex){
            Logger.Log(ex);
            return false;
        }
    }
}
