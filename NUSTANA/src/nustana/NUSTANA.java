/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import backendless.BackendlessClient;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.json.JSONObject;
import tools.FileSystem;
import tools.UI;

/**
 *
 * @author Abdul Rahman
 */
public class NUSTANA {
    public static final String CONFIGURATION_FILE_PATH = "appConfig.json";
    private static String ApplicationId;
    private static String SecretKey;
    private static BackendlessClient client;
    //Application entry point
    public static void main(String[] args) throws Exception {
        initializeLogger();
        initializeUI();
        Logger.Log("Application initialized!");
        if(!loadConfig()) {
            //TODO: Reconfiguration
            UI.ErrMsg("Unable to load configuration from file \""+CONFIGURATION_FILE_PATH+"\".","Error!");
            ServerConfig form = new ServerConfig();
            form.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    UI.InfoMsg("Please restart application.", "Configured!");
                }
            });
            form.setVisible(true);
        }
        else{
            Logger.Log("Application configured!");
            Logger.Log("Initializing backendless client.");
            client = new BackendlessClient(ApplicationId, SecretKey);
            new Login().setVisible(true);
        }
        (new ProfileInfoBox("8A1183F7-EE0A-DE9F-FF82-8352D8F61000")).setVisible(true);
    }
    //Initialize logger
    private static void initializeLogger(){
        Logger.initialize();
    }
    //Changes UI Theme
    private static void initializeUI(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    //Load configuration
    private static boolean loadConfig(){
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
    //Getter
    public static BackendlessClient getClient(){
        return client;
    }
}
