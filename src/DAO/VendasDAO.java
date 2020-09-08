package DAO;

import Model.Vendas;
import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class VendasDAO {

    private Connection con;

    public VendasDAO() {
        //open connection
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     * register a new sell
     * @param obj 
     */
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * return a last sell
     * @return 
     */
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
