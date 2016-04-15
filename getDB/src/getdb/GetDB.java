/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdb;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linco
 */
public class GetDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //Endereço raiz no MW
        //String start = "oferta_campus.aspx";
        //Endereço raiz do campus Darcy Ribeiro
        //String start = "oferta_dep.aspx?cod=1";
        //endereço do CIC no MW
        String start = "oferta_dis.aspx?cod=116";
        
        //Inicializando os arquivos necessários
        File file = new File("Banco/");
        if(!file.exists())
            file.mkdir();
        file = new File("Banco/db.txt");
        File fileold = new File("Banco/db-backup"+System.nanoTime()+".txt");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }else{
                file.renameTo(fileold);
                file = new File("Banco/db.txt");
                    file.createNewFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //Início
            getdb.navegador.acessodepartamento(start);
        } catch (MalformedURLException ex) {
            //Esse erro só ocorre em caso de a url acima estar incorreta
            return;
        }
    } 
}