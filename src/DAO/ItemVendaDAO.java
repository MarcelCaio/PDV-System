package DAO;

import Model.ItensVenda;
import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ItemVendaDAO {
    
    private Connection con;

    public ItemVendaDAO() {
        //open connection
        this.con = new ConnectionFactory().getConnection();
    }
    
    /**
     * register items in the current saleregister items in the current sale
     * @param obj 
     */
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
