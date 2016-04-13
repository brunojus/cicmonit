/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdb;

import java.net.MalformedURLException;

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
        //endereço do CIC no MW
        String start = "oferta_dis.aspx?cod=116";
        try {
            //Início
            getdb.navegador.spider(start);
        } catch (MalformedURLException ex) {
            //Esse erro só ocorre em caso de a url acima estar incorreta
            return;
        }
    } 
}