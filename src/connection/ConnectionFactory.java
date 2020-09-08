package connection;

import DAO.EmpresaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import util.Dados;

public class ConnectionFactory {
    public Connection getConnection(){
        Dados dados = new Dados();
        if (dados.verificarExistencia()){
            try {
            return DriverManager
                    .getConnection("jdbc:mysql:" + Dados.Read("Servidor"),
                            Dados.Read("UserBD") ,
                            "xdy@Mac3");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }else{
            return null;
        }
        
    }
    
    public Connection getConnectionSerial(){
        try {
//            System.out.println("Servidor conectado");
            return DriverManager
                    .getConnection("jdbc:mysql://sql175.main-hosting.eu/u349171340_seriais",
                            "u349171340_seriais" ,
                            "xdy@Mac3");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}