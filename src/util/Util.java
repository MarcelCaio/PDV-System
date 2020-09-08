/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author marce
 */
public class Util {
    
    public void LimpaTela(JPanel jInternalFrame){
        Component components[] = jInternalFrame.getComponents();
        for (Component component : components){
            if(component instanceof JTextField){
                ((JTextField) component).setText(null);
            }
        }
    }
    
}
