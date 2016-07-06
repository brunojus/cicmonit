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
 * @author Lincoln
 */
public class GetDB {
	
	//Indices
	public static int Camp = 0, Dep = 0, Dis = 0, Tur = 0, Hor = 0, Prof = 0;
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
        Dep = 1;
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
            /*
            writer.println("CREATE TABLE \"campus\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \"codigo\" integer, \"nome\" varchar);");
            writer.println("CREATE TABLE \"departamentos\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \"codigo\" integer, \"sigla\" varchar, \"nome\" varchar, \"campu_id\" integer);");
            writer.println("CREATE TABLE \"disciplinas\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \"codigo\" integer, \"nome\" varchar, \"departamento_id\" integer);");
            writer.println("CREATE TABLE \"turmas\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \"nome\" varchar, \"disciplina_id\" integer);");
            writer.println("CREATE TABLE \"horarios\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \"nome\" varchar, \"turma_id\" integer);");
            writer.println("CREATE TABLE "docentes_turmas" ("docente_id" integer NOT NULL, "turma_id" integer NOT NULL);");
            writer.println("CREATE TABLE "docentes" ("id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "nome" varchar, "user_id" integer);");
            */
            //Necessário por nao comessar da raiz
            writer.println("INSERT INTO \"campus\" VALUES(1,1,'Darcy Ribeiro');");
            writer.println("INSERT INTO \"departamentos\" VALUES(1,116,'CIC','Departamento de Ciência de Computação',1);");
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
        	/*
        	writer.println("DELETE FROM sqlite_sequence;");
        	writer.println("INSERT INTO \"sqlite_sequence\" VALUES('campus',1);");
        	writer.println("INSERT INTO \"sqlite_sequence\" VALUES('departamentos',1);");
        	writer.println("INSERT INTO \"sqlite_sequence\" VALUES('disciplinas',2);");
        	writer.println("INSERT INTO \"sqlite_sequence\" VALUES('turmas',2);");
        	writer.println("INSERT INTO \"sqlite_sequence\" VALUES('horarios',5);");
        	writer.println("INSERT INTO \"sqlite_sequence\" VALUES('professores_das_disciplinas',2);");
        	writer.println("CREATE UNIQUE INDEX \"unique_schema_migrations\" ON \"schema_migrations\" (\"version\");");
        	writer.println("CREATE INDEX \"index_professors_on_turma_id\" ON \"professors\" (\"turma_id\");");
        	writer.println("CREATE UNIQUE INDEX \"index_professors_on_email\" ON \"professors\" (\"email\");");
        	writer.println("CREATE UNIQUE INDEX \"index_professors_on_reset_password_token\" ON \"professors\" (\"reset_password_token\");");
        	writer.println("CREATE INDEX \"index_departamentos_on_campu_id\" ON \"departamentos\" (\"campu_id\");");
        	writer.println("CREATE INDEX \"index_disciplinas_on_departamento_id\" ON \"disciplinas\" (\"departamento_id\");");
        	writer.println("CREATE INDEX \"index_turmas_on_disciplina_id\" ON \"turmas\" (\"disciplina_id\");");
        	writer.println("CREATE INDEX \"index_horarios_on_turma_id\" ON \"horarios\" (\"turma_id\");");
        	writer.println("CREATE INDEX \"index_docentes_turmas_on_docente_id\" ON \"docentes_turmas\" (\"docente_id\");");
			writer.println("CREATE INDEX \"index_docentes_turmas_on_turma_id\" ON \"docentes_turmas\" (\"turma_id\");");
			*/
            writer.println("COMMIT;");
            writer.close();
        }catch (IOException ex) {
            //Seria bom um tratamento de erro aqui
            Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}