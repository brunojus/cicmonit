package getdb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        file = new File("Banco/db.sql");
        File fileold = new File("Banco/db-backup"+System.nanoTime()+".sql");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }else{
                file.renameTo(fileold);
                file = new File("Banco/db.sql");
                    file.createNewFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            //Cabeçalho
            writer.println("PRAGMA foreign_keys=OFF;");
            writer.println("BEGIN TRANSACTION;");
            writer.println("CREATE TABLE \"campus\" (\"id_cam\" integer,\"Nome\" varchar);");
            writer.println("CREATE TABLE \"departamentos\" (\"id_dep\" integer, \"Sigla\" varchar, \"Nome\" varchar,\"id_cam\" integer);");
            writer.println("CREATE TABLE \"disciplinas\" (\"id_dis\" integer, \"nome\" varchar, \"id_dep\" integer);");
            writer.println("CREATE TABLE \"turmas\" (\"id_tur\" varchar,\"id_dis\" integer);");
            writer.println("CREATE TABLE \"horarios\" (\"horario_e_local\" varchar,\"id_dis\" integer,\"id_tur\" varchar);");
            writer.println("CREATE TABLE \"professores\" (\"professor\" varchar, \"id_dis\" integer, \"id_tur\" varchar);");
            //Necessário por nao comessar da raiz
            writer.println("INSERT INTO \"campus\" VALUES(1,'Darcy Ribeiro');");
            writer.println("INSERT INTO \"departamentos\" VALUES(116,'CIC','Departamento de Ciencia da Computacao',1);");
            writer.close();
        }catch (IOException ex) {
            //Seria bom um tratamento de erro aqui
            Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //Início
            getdb.navegador.acessodepartamento(start);
        } catch (MalformedURLException ex) {
            //Esse erro só ocorre em caso de a url acima estar incorreta
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            //Finalizando
            writer.println("COMMIT;");
            writer.close();
        }catch (IOException ex) {
            //Seria bom um tratamento de erro aqui
            Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}