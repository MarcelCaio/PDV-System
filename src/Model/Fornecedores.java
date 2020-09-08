package Model;
/**
 * a data model of suppliers
 * @author marce
 */
public class Fornecedores extends Clientes {
    
    @Override
    public String toString(){
        return this.getId() + " - " + this.getNome();
    }
}
