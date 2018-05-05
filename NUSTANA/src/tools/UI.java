/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Abdul Rahman
 */
public class UI {
    public static void CloseFrame(JFrame frame){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
