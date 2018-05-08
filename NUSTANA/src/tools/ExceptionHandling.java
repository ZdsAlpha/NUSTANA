/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.swing.JOptionPane;
import nustana.Logger;
import java.io.StringWriter;
import java.io.PrintWriter;

/**
 *
 * @author Abdul Rahman
 */
public class ExceptionHandling {
    public static void ShowException(Exception ex){
        Logger.Log(ex);
        JOptionPane.showMessageDialog(null,ex.toString(), "Error!",JOptionPane.ERROR_MESSAGE);
    }
    public static void ShowException(Exception ex,String message){
        Logger.Log(ex);
        JOptionPane.showMessageDialog(null,message+"\n"+ex.toString(), "Error!",JOptionPane.ERROR_MESSAGE);
    }
    public static void ShowExceptionWithStack(Exception ex){
        Logger.Log(ex);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String message = sw.toString();
        JOptionPane.showMessageDialog(null,message, "Error!",JOptionPane.ERROR_MESSAGE);
    }
    public static void ShowExceptionWithStack(Exception ex,String message){
        Logger.Log(ex);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println(message);
        ex.printStackTrace(pw);
        String out = sw.toString();
        JOptionPane.showMessageDialog(null,out, "Error!",JOptionPane.ERROR_MESSAGE);
    }
}
