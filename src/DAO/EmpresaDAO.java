package DAO;

import Model.Empresa;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import view.Login;

public class EmpresaDAO {

    private Connection con;

    public EmpresaDAO() {
        //open db connection
        this.con = new ConnectionFactory().getConnectionSerial();
    }

    /**
     * search for company information using the serial key
     * @param serial
     * @return
     * @throws ParseException 
     */
    public boolean buscarInfoEmpresa(String serial) throws ParseException {
        try {
            
            String sqlList = "select * from  tb_seriais where serial='" + serial + "';";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
            ResultSet rs = stmt.executeQuery();

            Empresa empresa = new Empresa();
            boolean b = false;
            while (rs.next()) {
                
                //Download company information from db
                empresa.setId(rs.getInt("id"));
                empresa.setNome_fantasia(rs.getString("nome_fantasia"));
                empresa.setValidade(rs.getString("validade"));

                //validate 
                Date validade, hoje;
                Date dataSistema = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                validade = sdf.parse(rs.getString("validade"));
                hoje = sdf.parse(sdf.format(dataSistema));

                if (validade.after(hoje) || validade.equals(hoje)) {
                    b = true;
                    Login login = new Login();
                    login = new Login();
                    login.empresa = rs.getString("nome_fantasia");
                } else {
                    JOptionPane.showMessageDialog(null, "Licença vencida.\nConsulte nossa central.");
                    b = false;
                }

            }
            stmt.close();
            return b;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
