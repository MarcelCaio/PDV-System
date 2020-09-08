/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ItensVenda;
import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class ItemVendaDAO {
    
    private Connection con;

    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastraItem(ItensVenda obj){
        try {
            
            String sql = "insert into tb_itensvendas (venda_id, produto_id, qtd, subtotal) values(?, ?, ?, ?);";
            
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getVenda());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setFloat(3 , obj.getQtd());
            stmt.setDouble(4, obj.getTotal());
            
            stmt.execute();
            stmt.close();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
