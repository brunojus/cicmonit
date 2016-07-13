package br.unb.cicmonit.bolsas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscreveResultado {
	LeDados dados;
	
	public EscreveResultado(LeDados dados){
		this.dados = dados;
	}
	
	public void Escrever (){
		String tobewritten = "";
		HashMap<Integer, Candidato> candidatos;
		File file = new File("AlocaBolsas/result.sql");
		if(!file.exists())
            file.mkdir();
        file = new File("AlocaBolsas/result.sql");
        File fileold = new File("AlocaBolsas/result-backup"+System.nanoTime()+".sql");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }else{
                file.renameTo(fileold);
                file = new File("AlocaBolsas/result.sql");
                    file.createNewFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(EscreveResultado.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            //Cabeçalho
            writer.println("PRAGMA foreign_keys=OFF;");
            writer.println("BEGIN TRANSACTION;");
	        tobewritten = "REPLACE INTO \"candidatos\" VALUES(";
	        candidatos = dados.getCandidatos();
	        for(int i = 1; i<= candidatos.size();i++){
	        	 	writer.println(tobewritten+candidatos.get(i).getId()+","+
	             			candidatos.get(i).getMencao()+","+candidatos.get(i).getAlunoId()+","+
	             			candidatos.get(i).getAvaliacao()+");");
	        }
            //Finalizando
            writer.println("COMMIT;");
            writer.close();
        }catch (IOException ex) {
            //Seria bom um tratamento de erro aqui
            Logger.getLogger(EscreveResultado.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
