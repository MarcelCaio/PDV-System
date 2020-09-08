/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Fornecedores;
import connection.ConnectionFactory;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author marce
 */
public class FornecedoresDAO {
    private Connection con;

    public FornecedoresDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarFornecedor(Fornecedores obj) {
        try {
            String sql = "insert into tb_fornecedores ("
                    + "nome, cnpj, ie, email, celular, endereco,"
                    + "numero, complemento, bairro, cidade) values("
                    + "?,?,?,?,?,?,?,?,?,?);";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpfcnpj());
            stmt.setString(3, obj.getIe());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getEndereco());
            stmt.setInt(7, obj.getNumero());
            stmt.setString(8, obj.getComplemento());
            stmt.setString(9, obj.getBairro());
            stmt.setString(10, obj.getCidade());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    public void alterarFornecedor(Fornecedores obj) {

        try {
            String sql = "update tb_fornecedores set nome = ?, cnpj = ?, ie = ?, email = ?, celular = ?"
                    + ", endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ? where id = ?;";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpfcnpj());
            stmt.setString(3, obj.getIe());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getEndereco());
            stmt.setInt(7, obj.getNumero());
            stmt.setString(8, obj.getComplemento());
            stmt.setString(9, obj.getBairro());
            stmt.setString(10, obj.getCidade());
            stmt.setInt(11, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void excluirFornecedor(Fornecedores obj) {

        try {
            String sql = "delete from tb_fornecedores where id = ?";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    public List<Fornecedores> listarFornecedores() {

        try {
            //criar lista
            List<Fornecedores> lista = new ArrayList<>();

            //comando sql
            String sqlList = "select id, nome, celular, email from  tb_fornecedores";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores fornecedores = new Fornecedores();
                fornecedores.setId(rs.getInt("id"));
                fornecedores.setNome(rs.getString("nome"));
                fornecedores.setCelular(rs.getString("celular"));
                fornecedores.setEmail(rs.getString("email"));

                lista.add(fornecedores);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public Fornecedores exibirFornecedor(String id) {
        try {
            //comando sql
            String sqlList = "select * from  tb_fornecedores where id=" + id + ";";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
//            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            Fornecedores fornecedores = new Fornecedores();
            while (rs.next()) {
                fornecedores.setId(rs.getInt("id"));
                fornecedores.setNome(rs.getString("nome"));
                fornecedores.setCpfcnpj(rs.getString("cnpj"));
                fornecedores.setIe(rs.getString("ie"));
                fornecedores.setEmail(rs.getString("email"));
                fornecedores.setCelular(rs.getString("celular"));
                fornecedores.setEndereco(rs.getString("endereco"));
                fornecedores.setNumero(rs.getInt("numero"));
                fornecedores.setComplemento(rs.getString("complemento"));
                fornecedores.setBairro(rs.getString("bairro"));
                fornecedores.setCidade(rs.getString("cidade"));
                
            }
            stmt.close();
            return fornecedores;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public List<Fornecedores> PesquisarFornecedores(String tipo, String dado) {

        try {
            //criar lista
            List<Fornecedores> lista = new ArrayList<>();
//            System.out.println("tipo: " + tipo + " * Dado: " + dado);

            //comando sql
            String sqlList = null;
            switch (tipo) {
                case "ID":
                    sqlList = "select id, nome, celular, email from  tb_fornecedores where id like " + dado + ";";
                    break;
                case "Nome":
                    sqlList = "select id, nome, celular, email from  tb_fornecedores where nome like " + dado + ";";
                    break;
                default:
                    break;
            }

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores fornecedores = new Fornecedores();
                fornecedores.setId(rs.getInt("id"));
                fornecedores.setNome(rs.getString("nome"));
                fornecedores.setCelular(rs.getString("celular"));
                fornecedores.setEmail(rs.getString("email"));

                lista.add(fornecedores);

            }
            stmt.close();
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public Fornecedores nomeFornecedor(String dado) {

        try {
            //comando sql
            System.out.println("fornecedor: "+ dado);
            String sqlList = "select * from  tb_fornecedores where nome = '" + dado + "';";
            
            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            Fornecedores fornecedores = new Fornecedores();
            
            while (rs.next()) {
                fornecedores.setId(rs.getInt("id"));
                fornecedores.setNome(rs.getString("nome"));
                fornecedores.setCpfcnpj(rs.getString("cnpj"));
                fornecedores.setIe(rs.getString("ie"));
                fornecedores.setEmail(rs.getString("email"));
                fornecedores.setCelular(rs.getString("celular"));
                fornecedores.setEndereco(rs.getString("endereco"));
                fornecedores.setNumero(rs.getInt("numero"));
                fornecedores.setComplemento(rs.getString("complemento"));
                fornecedores.setBairro(rs.getString("bairro"));
                fornecedores.setCidade(rs.getString("cidade"));
            }
            stmt.close();
            return fornecedores;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
