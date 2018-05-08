/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdul Rahman
 */
public class UI {
    public static void InfoMsg(String message,String title){
        Msgbox(message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void ErrMsg(String message,String title){
        Msgbox(message, title, JOptionPane.ERROR_MESSAGE);
    }
    public static void Msgbox(String message,String title,int type){
        JOptionPane.showMessageDialog(null, message, title, type);
    }
    public static void CloseFrame(JFrame frame){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    public static void ShowDilague(JFrame current,JFrame frame){
        current.setVisible(false);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                current.setVisible(true);
            }
        });
    }
}
