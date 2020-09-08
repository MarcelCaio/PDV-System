/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author marce
 */
public class Fornecedores extends Clientes {
    
    @Override
    public String toString(){
        return this.getId() + " - " + this.getNome();
    }
}
