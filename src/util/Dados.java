/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author marce
 */
public class Dados {
    
    public boolean verificarExistencia(){
        String caminho = "C:\\ProtegoInfo/config.txt";
        File config = new File(caminho);
        return config.exists();
    }

    public static String Read(String dado) throws IOException {
        String caminho = "C:\\ProtegoInfo/config.txt";
        File config = new File(caminho);
        if (config.exists()) {
            //caso o arquivo exista
            try (BufferedReader buffRead = new BufferedReader(new FileReader(caminho))) {
                String linha = "";
                String info = "";
                while (true) {
                    if (linha != null) {
                        if (linha.equals(dado)) {
                            linha = buffRead.readLine();
                            info = linha;
                        }
                    } else {
                        break;
                    }
                    linha = buffRead.readLine();
                }
                return info;
            }
        } else {
            //caso o arquivo não exista
            return null;

        }
    }

    public static void write(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Escreva algo: ");
        linha = in.nextLine();
        buffWrite.append(linha + "\n");
        buffWrite.close();
    }

}
