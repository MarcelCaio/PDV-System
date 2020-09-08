/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class TestaConexao {
    
    public boolean teste() {
        try {
            if (new ConnectionFactory().getConnection() != null){
                return true;
            }else{
                return false;
            }
//            JOptionPane.showMessageDialog(null, "Conectado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro!: " + e);
            return false;
        }
    }
}
