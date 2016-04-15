/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linco
 */
public class navegador {
    //Navega através da arvore de links ,retornando Campus, Departamentos e/ou Matérias
    static void acessodepartamento(String link) throws MalformedURLException{
        String tobewritten = "", Num = "", NumSup="";
        int flagwriten = 0;
        //condiçao para parar antes de acessar as turmas, pois nelas é outro formato de html
        //o return sera substituido pela funçao de acesso as turmas
        if (link.contains("oferta_dados.aspx?"))
            //acessarturmas();
            return;
        //Url da raiz, todos os links sao somados à essa url
        String startlink = "https://www.matriculaweb.unb.br/matriculaweb/graduacao/";
        URL url = new URL(startlink.concat(link));
        String html;
        String [] parte1, parte2=null;
        List<String> filtrado = new ArrayList<>();
        //Todo o bloco necessário do html é lido como uma string
        try{
            html = readblock(url);
        } catch (IOException ex) {
            //Seria interessante o tratamento de um erro de comunicacao com o MW ou de falha no acesso a memória
            return;
        }
        //Ocorre aqui a divisao e limpeza da string nas respectivas partes removendo a marcaçao html
        parte1 = html.split("</tr>");
        for(int i = 0; i<parte1.length; i++){
            if(!(parte1[i].contains("<!-- TABELA MEIO -->") || parte1[i].contains("<!-- FIM TABELA MEIO -->"))){
                parte2 = parte1[i].split("<td>");
                for(int j = 0; j<parte2.length; j++){
                    if(!(parte2[j].contains("<img") || parte2[j].contains("</b"))){
                        if(!parte2[j].contains("<tr")){
                            filtrado.add(parte2[j].replaceAll("<a href=", "").replaceAll("</td>", "").replaceAll("</a>", ""));
                        }
                    }
                }
            }
        }
        //Separaçao dos links dos nomes dos campus, matérias ou departamentos
        // ocorre também a navegaçao por recursividade
        File file = new File("Banco/db.txt");
            for(int i = 0; i<filtrado.size(); i++){
                //Estas linhas ditam a estrutura inicial do Banco
                if(link.contains("oferta_dep")&& flagwriten == 0){
                    Num = parte1[0].substring(parte1[0].indexOf("cod=")+4);
                    tobewritten = "insert into departamento values (";
                    flagwriten = 1;
                }
                if(link.contains("oferta_campus")&& flagwriten == 0){
                    Num = parte1[0].substring(parte1[0].indexOf("cod=")+4);
                    tobewritten = "insert into campus values (";
                    flagwriten = 1;
                }
                if(link.contains("oferta_dis")){
                     tobewritten = "insert into disciplinas values (";
                    flagwriten = 1;
                }
                try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                if(filtrado.get(i).contains(">")){
                    parte1 = filtrado.get(i).split(">");
                    filtrado.set(i, parte1[1]);
                    //Estas linhas são necessárias para navegação a partir de um departamento específico
                    //devido à estrutura distinta da marcação
                    if (link.contains("oferta_dis")){
                        Num = parte1[0].substring(parte1[0].indexOf("cod=")+4,parte1[0].indexOf("&dep="));
                        NumSup = parte1[0].substring(parte1[0].indexOf("dep=")+4);
                        writer.println(tobewritten+Num+",'"+filtrado.get(i)+"',"+NumSup+");");
                        flagwriten = 0;
                    }
                    //Estas linhas são necessárias para navegação a partir de um campus específico
                    //devido à estrutura distinta da marcação
                    else if(link.contains("oferta_dep")){
                        Num = parte1[0].substring(parte1[0].indexOf("cod=")+4);
                        NumSup = link.substring(parte1[0].indexOf("cod=")+4);
                        writer.println(tobewritten+"'"+filtrado.get(i)+"',"+NumSup+");");
                        flagwriten = 0;
                    }else{
                    //Estas linhas são necessárias para navegação a partir do início das ofertas do MW
                    //devido à estrutura distinta da marcação
                        Num = parte1[0].substring(parte1[0].indexOf("cod=")+4);
                        writer.println(tobewritten+"'"+filtrado.get(i)+"');");
                        flagwriten = 0;
                    }
                    writer.close();
                    acessodepartamento(parte1[0]);
                }else{
                    //esta linha é necessária para navegação a partir do campus, pois há a necessidade
                    //de uma ordem entre números identificadores, siglas e nomes
                    try{
                        Integer.parseInt(filtrado.get(i));
                        tobewritten += filtrado.get(i) +",";
                        flagwriten = 1;
                    }catch(NumberFormatException ex){
                        tobewritten += "'"+filtrado.get(i)+"',";
                        flagwriten = 1;
                    }
                }
            } catch (IOException ex) {
                //Seria bom um tratamento de erro aqui
                Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Funçao para acessar o html da turmas, retornando letra da turma horário e professor
    static void acessarturmas (){
        return;
    }

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
