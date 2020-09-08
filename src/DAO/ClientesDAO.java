/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Clientes;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class ClientesDAO {

    private Connection con;

    public ClientesDAO() {
        //open connection to DB
        this.con = new ConnectionFactory().getConnection();
    }

    
    //register new customers in the DB
    public void cadastrarCliente(Clientes obj) {
        try {
            String sql = "insert into tb_clientes ("
                    + "nome, celular, email, pessoa, cpfcnpj, ie, endereco,"
                    + "numero, complemento, referencia, bairro, cidade) values("
                    + "?,?,?,?,?,?,?,?,?,?,?,?);";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCelular());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getPessoa());
            stmt.setString(5, obj.getCpfcnpj());
            stmt.setString(6, obj.getIe());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getReferencia());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    //Alter customers in DB
    public void alterarClinte(Clientes obj) {

        try {
            String sql = "update tb_clientes set nome = ?, celular = ?, email = ?, pessoa = ?,"
                    + " cpfcnpj = ?, ie = ?, endereco = ?,"
                    + " numero = ?, complemento = ?, referencia = ?"
                    + ", bairro = ?, cidade = ? where id = ?;";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCelular());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getPessoa());
            stmt.setString(5, obj.getCpfcnpj());
            stmt.setString(6, obj.getIe());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getReferencia());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setInt(13, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Delete customers in DB
    public void excluirClientes(Clientes obj) {

        try {
            String sql = "delete from tb_clientes where id = ?";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    //List all customers in to table
    public List<Clientes> listarClientes() {

        try {
            //Create List
            List<Clientes> lista = new ArrayList<>();

            //SQL command
            String sqlList = "select id, nome, celular, pessoa, cpfcnpj from  tb_clientes";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes clientes = new Clientes();
                clientes.setId(rs.getInt("id"));
                clientes.setNome(rs.getString("nome"));
                clientes.setCelular(rs.getString("celular"));
                clientes.setPessoa(rs.getString("pessoa"));
                clientes.setCpfcnpj(rs.getString("cpfcnpj"));

                lista.add(clientes);

            }
            stmt.close();

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //accesses in DB all the information of a customer through the id
    public Clientes exibirCliente(String id) {
        try {
           
            String sqlList = "select * from  tb_clientes where id=" + id + ";";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
            ResultSet rs = stmt.executeQuery();

            Clientes clientes = new Clientes();
            while (rs.next()) {
                clientes.setId(rs.getInt("id"));
                clientes.setNome(rs.getString("nome"));
                clientes.setCelular(rs.getString("celular"));
                clientes.setEmail(rs.getString("email"));
                clientes.setPessoa(rs.getString("pessoa"));
                clientes.setCpfcnpj(rs.getString("cpfcnpj"));
                clientes.setIe(rs.getString("ie"));
                clientes.setEndereco(rs.getString("endereco"));
                clientes.setNumero(rs.getInt("numero"));
                clientes.setComplemento(rs.getString("complemento"));
                clientes.setReferencia(rs.getString("referencia"));
                clientes.setBairro(rs.getString("bairro"));
                clientes.setCidade(rs.getString("cidade"));
            }
            stmt.close();
            return clientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //search the DB through the id, name or CPF/CNPJ
    public List<Clientes> PesquisarClientes(String tipo, String dado) {

        try {
            //Create List
            List<Clientes> lista = new ArrayList<>();
            System.out.println("tipo: " + tipo + " * Dado: " + dado);

            //switch case search
            String sqlList = null;
            switch (tipo) {
                case "ID":
                    sqlList = "select id, nome, celular, pessoa, cpfcnpj from  tb_clientes where id like " + dado + ";";
                    break;
                case "Nome":
                    sqlList = "select id, nome, celular, pessoa, cpfcnpj from  tb_clientes where nome like " + dado + ";";
                    break;
                case "CPF/CNPJ":
                    sqlList = "select id, nome, celular, pessoa, cpfcnpj from  tb_clientes where cpfcnpj like " + dado + ";";
                    break;
                default:
                    break;
            }

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes clientes = new Clientes();
                clientes.setId(rs.getInt("id"));
                clientes.setNome(rs.getString("nome"));
                clientes.setCelular(rs.getString("celular"));
                clientes.setPessoa(rs.getString("pessoa"));
                clientes.setCpfcnpj(rs.getString("cpfcnpj"));

                lista.add(clientes);

            }
            stmt.close();
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //searches all the information of a customer searching by cpf
    public Clientes exibirClienteCpf(String cpf) {
        try {
            String sqlList = "select * from  tb_clientes where cpfcnpj like '" + cpf + "';";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
            ResultSet rs = stmt.executeQuery();

            Clientes clientes = new Clientes();
            while (rs.next()) {
                clientes.setId(rs.getInt("id"));
                clientes.setNome(rs.getString("nome"));
                clientes.setCelular(rs.getString("celular"));
                clientes.setEmail(rs.getString("email"));
                clientes.setPessoa(rs.getString("pessoa"));
                clientes.setCpfcnpj(rs.getString("cpfcnpj"));
                clientes.setIe(rs.getString("ie"));
                clientes.setEndereco(rs.getString("endereco"));
                clientes.setNumero(rs.getInt("numero"));
                clientes.setComplemento(rs.getString("complemento"));
                clientes.setReferencia(rs.getString("referencia"));
                clientes.setBairro(rs.getString("bairro"));
                clientes.setCidade(rs.getString("cidade"));
            }
            stmt.close();
            return clientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
