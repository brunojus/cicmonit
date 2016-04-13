/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Linco
 */

public class ReadPage {
    //Essa funçao retira só a parte necessária do html da pagina.
    //A parte necessaria está contida entre os comentarios TABELA MEIO e FIM TABELA MEIO
    static String readblock(URL url) throws IOException{
        int read = 0;
        InputStream source;
        BufferedReader buff;
        String linebuff, line = "";
        source = url.openStream();
        buff = new BufferedReader(new InputStreamReader(source));
        while ((linebuff = buff.readLine()) != null) {
            if (linebuff.matches("<!-- TABELA MEIO -->"))
                read = 1;
            if (read == 1){
                line += linebuff;
            }
            if (linebuff.matches("<!-- FIM TABELA MEIO -->"))
                read = 0;
        }
        if (source != null) 
            source.close();
        return line;
    }
}