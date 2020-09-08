/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Empresa;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import view.Login;

/**
 *
 * @author marce
 */
public class EmpresaDAO {

    private Connection con;

    public EmpresaDAO() {
        this.con = new ConnectionFactory().getConnectionSerial();
    }

    public boolean buscarInfoEmpresa(String serial) throws ParseException {
        try {
            //comando sql
            String sqlList = "select * from  tb_seriais where serial='" + serial + "';";

            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
            ResultSet rs = stmt.executeQuery();

            Empresa empresa = new Empresa();
            boolean b = false;
            while (rs.next()) {
                
                empresa.setId(rs.getInt("id"));
                empresa.setNome_fantasia(rs.getString("nome_fantasia"));
                empresa.setValidade(rs.getString("validade"));

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
//                    System.out.println("empresa: " + login.empresa);
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
    
//    public Empresa loginEmpresa(String serial) throws ParseException {
//        try {
//            //comando sql
//            String sqlList = "select * from  tb_seriais where serial='" + serial + "';";
//
//            java.sql.PreparedStatement stmt = con.prepareStatement(sqlList);
//            ResultSet rs = stmt.executeQuery();
//
//            Empresa empresa = new Empresa();
//            
//            while (rs.next()) {
//                
//                empresa.setId(rs.getInt("id"));
//                empresa.setNome_fantasia(rs.getString("nome_fantasia"));
//                empresa.setValidade(rs.getString("validade"));
//
//                Date validade, hoje;
//                Date dataSistema = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                validade = sdf.parse(rs.getString("validade"));
//                hoje = sdf.parse(sdf.format(dataSistema));
//
//                if (validade.after(hoje) || validade.equals(hoje)) {
//                    b = true;
//                    Login login = new Login();
//                    login = new Login();
//                    login.empresa = rs.getString("nome_fantasia");
////                    System.out.println("empresa: " + login.empresa);
//                } else {
//                    System.out.println("data não passou");
//                    b = false;
//                }
//
//            }
//
//            return b;
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//            return false;
//        }
//    }

}
