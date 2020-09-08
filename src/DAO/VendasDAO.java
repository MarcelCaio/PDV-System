/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Vendas;
import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author marce
 */
public class VendasDAO {

    private Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //cadastrar venda
    public void cadastrarVenda(Vendas obj) {
        try {
            String sql = "insert into tb_vendas(hora, data_venda, total_venda, "
                    + "lucro, cliente_id, funcionario, status) values (?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);

            stmt.setString(1, obj.getHora());
            stmt.setString(2, obj.getData());
            stmt.setDouble(3, obj.getTotal());
            stmt.setDouble(4, obj.getLucro());
            if(obj.getCliente() != null){
                stmt.setInt(5, obj.getCliente().getId());
            }else{
                stmt.setString(5, null);
            }
            
            stmt.setInt(6, obj.getFuncionario().getId());
            stmt.setString(7, obj.getStatus());

            stmt.execute();
            stmt.close();

//            JOptionPane.showMessageDialog(null, "Venda Salva!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //ultima venda
    public int ultimaVenda() {
        try {
            int idVenda = 0;
            
            String sql = "select max(id) id from tb_vendas;";

            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                idVenda = p.getId();
            }
            
            return idVenda;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
