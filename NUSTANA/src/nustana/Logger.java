/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nustana;

import java.io.*;
import java.time.LocalDateTime;

/**
 *
 * @author Abdul Rahman
 */
public class Logger {
    public static final String LOG_FILE_PATH = "logs.txt";
    private static FileOutputStream stream;
    private static PrintWriter writer;
    public static synchronized boolean initialize(){
        try{
            stream = new FileOutputStream(LOG_FILE_PATH);
            writer = new PrintWriter(stream);
            return true;
        }catch(Exception ex){
            return false;
        }
    } 
    public static synchronized boolean deInitialize(){
        try{
            writer.flush();
            stream.flush();
            writer.close();
            stream.close();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public static synchronized boolean Log(String message){
        try{
            String time = LocalDateTime.now().toString();
            Thread thread = Thread.currentThread();
            System.out.println(time + "\t\t<" + thread.getName() + ":" + thread.getId() + ">\t" + message);
            writer.println(time + "\t\t<" + thread.getName() + ":" + thread.getId() + ">\t" + message);
            writer.flush();
            stream.flush();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public static synchronized boolean Log(Exception e){
        try{
            Log("Exception: " + e.getClass().getName());
            e.printStackTrace();
            e.printStackTrace(writer);
            writer.flush();
            stream.flush();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
