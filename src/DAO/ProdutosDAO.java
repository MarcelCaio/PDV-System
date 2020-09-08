/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Fornecedores;
import Model.Produtos;
import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author marce
 */
public class ProdutosDAO {

    private Connection con;

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarProduto(Produtos obj) {
        try {
            String sql = "insert into tb_produtos(codBarras, descricao, estoque, unidade,"
                    + "precoCompra, lucroPercen, lucroReal, precoVenda, ncm, ipi, icms,"
                    + "frete, precoPromo, percenPromo, validadePromo,"
                    + "for_id)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, obj.getCodBarras());
            stmt.setString(2, obj.getDescricao());
            stmt.setFloat(3, obj.getEstoque());
            stmt.setString(4, obj.getUnidade());
            stmt.setDouble(5, obj.getPrecoCompra());
            stmt.setFloat(6, obj.getLucroPercen());
            stmt.setDouble(7, obj.getLucroReal());
            stmt.setDouble(8, obj.getPrecoVenda());
            stmt.setInt(9, obj.getNcm());
            stmt.setInt(10, obj.getIpi());
            stmt.setInt(11, obj.getIcms());
            stmt.setFloat(12, obj.getFrete());
            stmt.setDouble(13, obj.getPrecoPromo());
            stmt.setFloat(14, obj.getPercenPromo());
            stmt.setString(15, obj.getValidadePromo());
            stmt.setInt(16, obj.getFornecedores().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    public List<Produtos> listarFuncionarios() {

        try {
            //criar lista
            List<Produtos> lista = new ArrayList<>();

            //comando sql
            String sqlList = "select p.codBarras, p.descricao, p.precoVenda, p.estoque, p.unidade, f.nome from  tb_produtos as p"
                    + " inner join tb_fornecedores as f on (p.for_id = f.id);";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos produtos = new Produtos();
                Fornecedores fornecedor = new Fornecedores();
                produtos.setCodBarras(rs.getString("p.codBarras"));
                produtos.setDescricao(rs.getString("p.descricao"));
                produtos.setPrecoVenda(rs.getDouble("p.precoVenda"));
                produtos.setEstoque(rs.getFloat("p.estoque"));
                produtos.setUnidade(rs.getString("unidade"));
                fornecedor.setNome(rs.getString("f.nome"));
                produtos.setFornecedores(fornecedor);

                lista.add(produtos);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public Produtos exibirProduto(String id) {
        try {
            //comando sql
            String sqlList = "select * from  tb_produtos where codBarras ='" + id + "';";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
//            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            Produtos produtos = new Produtos();
            while (rs.next()) {
                produtos.setId(rs.getInt("id"));
                produtos.setCodBarras(rs.getString("codBarras"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setEstoque(rs.getFloat("estoque"));
                produtos.setUnidade(rs.getString("unidade"));
                produtos.setPrecoCompra(rs.getDouble("precoCompra"));
                produtos.setLucroPercen(rs.getFloat("lucroPercen"));
                produtos.setLucroReal(rs.getDouble("lucroReal"));
                produtos.setPrecoVenda(rs.getDouble("precoVenda"));
                produtos.setNcm(rs.getInt("ncm"));
                produtos.setIpi(rs.getInt("ipi"));
                produtos.setIcms(rs.getInt("icms"));
                produtos.setFrete(rs.getFloat("frete"));
                produtos.setPrecoPromo(rs.getDouble("precoPromo"));
                produtos.setPercenPromo(rs.getFloat("percenPromo"));
                produtos.setValidadePromo(rs.getString("validadePromo"));

                String sqlFor = "select * from tb_fornecedores where id = " + rs.getInt("for_id") + ";";
                java.sql.PreparedStatement stmt2 = con.prepareStatement(sqlFor);
//            stmt.setString(1, id);
                ResultSet rs2 = stmt2.executeQuery();

                Fornecedores fornecedor = new Fornecedores();

                while (rs2.next()) {
                    fornecedor.setId(rs2.getInt("id"));
                    fornecedor.setNome(rs2.getString("nome"));
                    produtos.setFornecedores(fornecedor);
                }
                
            }
            stmt.close();
            return produtos;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void editarProduto(Produtos obj) {
        try {
            String sql = "update tb_produtos set codBarras = ?, descricao = ?, estoque = ?, unidade = ?,"
                    + "precoCompra = ?, lucroPercen = ?, lucroReal = ?, precoVenda = ?, ncm = ?, ipi = ?, icms = ?,"
                    + "frete = ?, precoPromo = ?, percenPromo = ?, validadePromo = ?,"
                    + "for_id = ? where id = ?;";

            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, obj.getCodBarras());
            stmt.setString(2, obj.getDescricao());
            stmt.setFloat(3, obj.getEstoque());
            stmt.setString(4, obj.getUnidade());
            stmt.setDouble(5, obj.getPrecoCompra());
            stmt.setFloat(6, obj.getLucroPercen());
            stmt.setDouble(7, obj.getLucroReal());
            stmt.setDouble(8, obj.getPrecoVenda());
            stmt.setInt(9, obj.getNcm());
            stmt.setInt(10, obj.getIpi());
            stmt.setInt(11, obj.getIcms());
            stmt.setFloat(12, obj.getFrete());
            stmt.setDouble(13, obj.getPrecoPromo());
            stmt.setFloat(14, obj.getPercenPromo());
            stmt.setString(15, obj.getValidadePromo());
            stmt.setInt(16, obj.getFornecedores().getId());
            stmt.setInt(17, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void excluirProduto(Produtos obj) {

        try {
            String sql = "delete from tb_produtos where id = ?";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    public List<Produtos> PesquisarProdutos(String tipo, String dado) {

        try {
            //criar lista
            List<Produtos> lista = new ArrayList<>();

            //comando sql
            String sqlList = null;
            String sql = null;
            switch (tipo) {
                case "Cód. Barras":
                    sqlList = "select p.codBarras, p.descricao, p.precoVenda, p.estoque, p.unidade, f.nome from  tb_produtos as p"
                            + " inner join tb_fornecedores as f on (p.for_id = f.id) where p.codBarras like " + dado + ";";
                    break;
                case "Descrição":
                    sqlList = "select p.codBarras, p.descricao, p.precoVenda, p.estoque, p.unidade, f.nome from  tb_produtos as p"
                            + " inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like " + dado + ";";
                    break;
                case "Fornecedor":
//                    sql = "select id from  tb_fornecedores where nome like " + dado + ";";
//                    java.sql.PreparedStatement stmt1 = con.prepareStatement(sqlList);
//
//                    ResultSet rs1 = stmt1.executeQuery();
//                    
//                    while (rs1.next()){
//                        Fornecedores fornecedor1 = new Fornecedores();
//                        int dado1 = rs1.getInt("id");
//                    }

                    sqlList = "select p.codBarras, p.descricao, p.precoVenda, p.estoque, p.unidade, f.nome from  tb_produtos as p"
                            + " inner join tb_fornecedores as f on (p.for_id = f.id) where f.nome like " + dado + ";";
                    break;
                default:
                    break;
            }

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos produtos = new Produtos();
                Fornecedores fornecedor = new Fornecedores();
                produtos.setCodBarras(rs.getString("p.codBarras"));
                produtos.setDescricao(rs.getString("p.descricao"));
                produtos.setPrecoVenda(rs.getDouble("p.precoVenda"));
                produtos.setEstoque(rs.getFloat("p.estoque"));
                produtos.setUnidade(rs.getString("unidade"));
                fornecedor.setNome(rs.getString("f.nome"));
                produtos.setFornecedores(fornecedor);

                lista.add(produtos);

            }
            stmt.close();
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
