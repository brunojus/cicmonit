package br.unb.cicmonit.bolsas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LimpaArquivo {
	public void limpar(String TEMP){
		List<String> linhas;
		Path path = Paths.get(TEMP);
		try{
			linhas = Files.readAllLines(path);
			for(int s = linhas.size()-1; s >= 0; s--) {
				if(!linhas.get(s).contains("INSERT INTO ")) {
					linhas.remove(s);
				}
			}
			File filenew = new File(TEMP+"new");
			filenew.createNewFile();
			File file = new File(TEMP);
	        File fileold = new File(TEMP+"old");
	        PrintWriter writer = new PrintWriter(new FileWriter(filenew, true));
            //Cabeçalho
            writer.println("PRAGMA foreign_keys=OFF;");
            writer.println("BEGIN TRANSACTION;");
            for(int s = 0; s < linhas.size(); s++) {
            	writer.println(linhas.get(s));
            }           
          //Finalizando
            writer.println("COMMIT;");
            writer.close();
            file.renameTo(fileold);
            filenew.renameTo(file);
            fileold.delete();
        }catch (IOException ex) {
            //Seria bom um tratamento de erro aqui
            Logger.getLogger(EscreveResultado.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
