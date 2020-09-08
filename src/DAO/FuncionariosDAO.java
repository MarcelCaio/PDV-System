package DAO;

import Model.Clientes;
import Model.Funcionarios;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.UserLogin;

public class FuncionariosDAO {

    private Connection con;

    public FuncionariosDAO() {
        //open connection
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     * Register employees in DB
     * @param obj 
     */
    public void cadastrarFuncionario(Funcionarios obj) {
        try {
            String sql = "insert into tb_funcionarios ("
                    + "nome, login, rg, cpf, email, senha, funcao, acesso, celular, "
                    + "endereco, numero, complemento, bairro, cidade) values("
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getLogin());
            stmt.setString(3, obj.getRg());
            stmt.setString(4, obj.getCpfcnpj());
            stmt.setString(5, obj.getEmail());
            stmt.setString(6, obj.getSenha());
            stmt.setString(7, obj.getFuncao());
            stmt.setString(8, obj.getAcesso());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getEndereco());
            stmt.setInt(11, obj.getNumero());
            stmt.setString(12, obj.getComplemento());
            stmt.setString(13, obj.getBairro());
            stmt.setString(14, obj.getCidade());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * List all employees in DB
     * @return 
     */
    public List<Funcionarios> listarFuncionarios() {

        try {
            
            List<Funcionarios> lista = new ArrayList<>();

            
            String sqlList = "select id, nome, funcao, acesso from  tb_funcionarios";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios funcionarios = new Funcionarios();
                funcionarios.setId(rs.getInt("id"));
                funcionarios.setNome(rs.getString("nome"));
                funcionarios.setFuncao(rs.getString("funcao"));
                funcionarios.setAcesso(rs.getString("acesso"));

                lista.add(funcionarios);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * Show all employees in DB
     * @param id
     * @return 
     */
    public Clientes exibirFuncionario(String id) {
        try {
            //comando sql
            String sqlList = "select * from  tb_funcionarios where id=" + id + ";";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
//            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            Funcionarios funcionarios = new Funcionarios();
            while (rs.next()) {
                funcionarios.setId(rs.getInt("id"));
                funcionarios.setNome(rs.getString("nome"));
                funcionarios.setLogin(rs.getString("login"));
                funcionarios.setRg(rs.getString("rg"));
                funcionarios.setCelular(rs.getString("celular"));
                funcionarios.setEmail(rs.getString("email"));
                funcionarios.setFuncao(rs.getString("funcao"));
                funcionarios.setAcesso(rs.getString("acesso"));
                funcionarios.setCpfcnpj(rs.getString("cpf"));
                funcionarios.setEndereco(rs.getString("endereco"));
                funcionarios.setNumero(rs.getInt("numero"));
                funcionarios.setComplemento(rs.getString("complemento"));
                funcionarios.setBairro(rs.getString("bairro"));
                funcionarios.setCidade(rs.getString("cidade"));
            }
            stmt.close();
            return funcionarios;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * alter employees in DB
     * @param obj 
     */
    public void alterarFuncionario(Funcionarios obj) {

        try {
            String sql = "update tb_funcionarios set nome = ?,login = ? , rg = ?, cpf = ?, "
                    + "email = ?, senha = ?, funcao = ?, acesso = ?, celular = ?, "
                    + "endereco = ?, numero = ?, complemento = ?, "
                    + "bairro = ?, cidade = ? where id = ?;";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getLogin());
            stmt.setString(3, obj.getRg());
            stmt.setString(4, obj.getCpfcnpj());
            stmt.setString(5, obj.getEmail());
            stmt.setString(6, obj.getSenha());
            stmt.setString(7, obj.getFuncao());
            stmt.setString(8, obj.getAcesso());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getEndereco());
            stmt.setInt(11, obj.getNumero());
            stmt.setString(12, obj.getComplemento());
            stmt.setString(13, obj.getBairro());
            stmt.setString(14, obj.getCidade());
            stmt.setInt(15, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * remove employees in DB
     * @param obj 
     */
    public void excluirFuncionario(Funcionarios obj) {

        try {
            String sql = "delete from tb_funcionarios where id = ?";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * list employees information in DB through the id, function, acess or name 
     * @param tipo
     * @param dado
     * @return 
     */
    public List<Funcionarios> PesquisarFuncionarios(String tipo, String dado) {

        try {
            //criar lista
            List<Funcionarios> lista = new ArrayList<>();
            System.out.println("tipo: " + tipo + " * Dado: " + dado);

            //comando sql
            String sqlList = null;
            switch (tipo) {
                case "ID":
                    sqlList = "select id, nome, funcao, acesso from  tb_funcionarios where id like " + dado + ";";
                    break;
                case "Nome":
                    sqlList = "select id, nome, funcao, acesso from  tb_funcionarios where nome like " + dado + ";";
                    break;
                case "Função":
                    sqlList = "select id, nome, funcao, acesso from  tb_funcionarios where funcao like " + dado + ";";
                    break;
                case "Acesso":
                    sqlList = "select id, nome, funcao, acesso from  tb_funcionarios where acesso like " + dado + ";";
                    break;
                default:
                    break;
            }

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios funcionarios = new Funcionarios();
                funcionarios.setId(rs.getInt("id"));
                funcionarios.setNome(rs.getString("nome"));
                funcionarios.setFuncao(rs.getString("funcao"));
                funcionarios.setAcesso(rs.getString("acesso"));

                lista.add(funcionarios);

            }
            stmt.close();
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * Login
     * @param login
     * @param senha
     * @return 
     */
    public boolean Logar(String login, String senha) {
        try {
            String sql = "select * from tb_funcionarios where login = ? and senha = ?;";

            java.sql.PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            Funcionarios funcionarios = new Funcionarios();

            while (rs.next()) {
                funcionarios.setId(rs.getInt("id"));
                funcionarios.setNome(rs.getString("nome"));
                funcionarios.setLogin(rs.getString("login"));
                funcionarios.setRg(rs.getString("rg"));
                funcionarios.setCelular(rs.getString("celular"));
                funcionarios.setEmail(rs.getString("email"));
                funcionarios.setFuncao(rs.getString("funcao"));
                funcionarios.setAcesso(rs.getString("acesso"));
                funcionarios.setCpfcnpj(rs.getString("cpf"));
                funcionarios.setEndereco(rs.getString("endereco"));
                funcionarios.setNumero(rs.getInt("numero"));
                funcionarios.setComplemento(rs.getString("complemento"));
                funcionarios.setBairro(rs.getString("bairro"));
                funcionarios.setCidade(rs.getString("cidade"));
                UserLogin.funcionario = funcionarios;
            }
            stmt.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

}
