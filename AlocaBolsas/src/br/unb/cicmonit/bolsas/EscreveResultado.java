package br.unb.cicmonit.bolsas;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscreveResultado {
	LeDados dados;
	
	public EscreveResultado(LeDados dados){
		this.dados = dados;
	}
	
	public void Escrever (){
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
	}
}
