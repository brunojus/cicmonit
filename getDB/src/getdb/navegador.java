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
import java.text.Normalizer;
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
        String tobewritten = "", Num, NumSup;
        int flagwriten = 0;
        //condiçao para parar antes de acessar as turmas, pois nelas é outro formato de html
        if (contains(link,"oferta_dados.aspx?")){
            Num = link.substring(link.indexOf("cod=")+4,link.indexOf("&dep="));
            acessarturmas(link, Integer.parseInt(Num));
            return;
        }
        //Url da raiz, todos os links sao somados à essa url
        String startlink = "https://www.matriculaweb.unb.br/matriculaweb/graduacao/";
        URL url = new URL(startlink.concat(link));
        String html;
        String [] parte1, parte2;
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
            parte1[i]= toalphanum(parte1[i]);
            if(contains(parte1[i],"AAO"))
                parte1[i].replace("AAO", "CAO");
            if(contains(parte1[i],"AAo"))
                parte1[i].replace("AAo", "cao");
        }
        for(int i = 0; i<parte1.length; i++){
            if(!contains(parte1[i],"TABELA MEIO")){
                parte2 = parte1[i].split("<td>");
                for(int j = 0; j<parte2.length; j++){
                    if(!(contains(parte2[j],"<img") || contains(parte2[j],"</b"))){
                        if(!contains(parte2[j],"<tr")){
                            filtrado.add(parte2[j].replaceAll("<a href=", "").replaceAll("</td>", "").replaceAll("</a>", ""));
                        }
                    }
                }
            }
        }
        //Separaçao dos links dos nomes dos campus, matérias ou departamentos
        // ocorre também a navegaçao por recursividade
        File file = new File("Banco/db.sql");
        for(int i = 0; i<filtrado.size(); i++){
            //Estas linhas ditam a estrutura inicial do Banco
            if(contains(link,"oferta_dep")&& flagwriten == 0){
                tobewritten = "INSERT INTO \"departamento\" VALUES(";
                flagwriten = 1;
            }
            if(contains(link,"oferta_campus")&& flagwriten == 0){
                tobewritten = "INSERT INTO \"campus\" VALUES(";
                flagwriten = 1;
            }
            if(contains(link,"oferta_dis")){
                 tobewritten = "INSERT INTO \"disciplinas\" VALUES(";
                flagwriten = 1;
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            if(contains(filtrado.get(i),">")){
                parte1 = filtrado.get(i).split(">");
                filtrado.set(i, parte1[1]);
                //Estas linhas são necessárias para navegação a partir de um departamento específico
                //devido à estrutura distinta da marcação
                if (contains(link,"oferta_dis")){
                    Num = parte1[0].substring(parte1[0].indexOf("cod=")+4,parte1[0].indexOf("&dep="));
                    NumSup = parte1[0].substring(parte1[0].indexOf("dep=")+4);
                    writer.println(tobewritten+Num+",'"+filtrado.get(i)+"',"+NumSup+");");
                    flagwriten = 0;
                }
                //Estas linhas são necessárias para navegação a partir de um campus específico
                //devido à estrutura distinta da marcação
                else if(contains(link,"oferta_dep")){
                    NumSup = link.substring(parte1[0].indexOf("cod=")+4);
                    writer.println(tobewritten+"'"+filtrado.get(i)+"',"+NumSup+");");
                    flagwriten = 0;
                }else{
                //Estas linhas são necessárias para navegação a partir do início das ofertas do MW
                //devido à estrutura distinta da marcação
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
    static void acessarturmas (String link, int NumDis) throws MalformedURLException{
        String startlink = "https://www.matriculaweb.unb.br/matriculaweb/graduacao/";
        URL url = new URL(startlink.concat(link));
        String html, tobewritten, professores, horarioelocal, turma = "";
        String [] parte1, parte2;
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
            parte1[i]= toalphanum(parte1[i]);
            if(contains(parte1[i],"AAO"))
                parte1[i].replace("AAO", "CAO");
            if(contains(parte1[i],"AAo"))
                parte1[i].replace("AAo", "cao");
        }
        for(int i = 0; i<parte1.length; i++){
            if(contains(parte1[i],"#FFFFFF")&&contains(parte1[i],"<b>")&&contains(parte1[i],"</b>")){
                filtrado.add(parte1[i].substring(parte1[i].indexOf("<b>")+3,parte1[i].indexOf("</b>")));
            }
            if((contains(parte1[i],"Horario:")||contains(parte1[i],"Local:"))&&!contains(parte1[i],"href=")){
                parte2 = parte1[i].split("</font></td>");
                for(int j = 0; j<parte2.length; j++){
                    if(contains(parte2[j],"Horario:")&&contains(parte2[j],"Local:")){
                        filtrado.add(parte2[j].substring(parte2[j].indexOf("green>")+6));
                        filtrado.add(parte2[j].substring(parte2[j].indexOf("Horario:")+9,parte2[j].indexOf("Local")));
                        filtrado.add(parte2[j].substring(parte2[j].indexOf("Local:")+7,parte2[j].indexOf("\"><font")));
                    }
                }
            }
            if(contains(parte1[i],"smoke><center>")){
                professores = parte1[i].substring(parte1[i].indexOf("smoke><center>")+14,parte1[i].indexOf("<br></center>"));
                if(contains(professores,"<br>")){
                    parte2=professores.split("<br>");
                    for(int j = 0; j<parte2.length; j++){
                        filtrado.add(parte2[j]);
                    }
                }else{
                    filtrado.add(professores);
                }
            }
        }
        File file = new File("Banco/db.sql");
        for(int i = 0; i<filtrado.size(); i++){
            //Insere a letra da turma na saida
            //Tratando a única exceção ainda existente
            if(contains(filtrado.get(i),"wrap"))
                filtrado.set(i, "DOM");
            if(filtrado.get(i).length()<3){
                turma = filtrado.get(i);
                tobewritten = "INSERT INTO \"turmas\" VALUES('"+turma+"'";
                tobewritten+=","+NumDis+");";
                try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                    writer.println(tobewritten);
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(filtrado.get(i).length()==3){
                //Dia
                horarioelocal="INSERT INTO \"horarios\" VALUES('"+filtrado.get(i)+" "; 
                i=i+1;
                if(i<filtrado.size()){
                    //Horario
                    horarioelocal+=filtrado.get(i)+" ";
                    i=i+1;
                    if(i<filtrado.size())
                        //Local (Sempre existe, mesmo que seja A definir)
                        horarioelocal+=filtrado.get(i);
                    horarioelocal+="',"+NumDis+",'"+turma+"');";
                    try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                        writer.println(horarioelocal);
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Caso tenha dia mas nao tenha horário nem local
                else{
                   horarioelocal+="',"+NumDis+",'"+turma+"');";
                   try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                        writer.println(horarioelocal);
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
            //Se nao é turma ou dia é nome de professor
            else{
                professores = "INSERT INTO \"professores\" VALUES('"+filtrado.get(i)+"',"+NumDis+",'"+turma+"');";
                try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                    writer.println(professores);
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static String toalphanum(final String str) {
	String limpa = Normalizer.normalize(str, Normalizer.Form.NFD);
	limpa = limpa.replaceAll("[^\\p{ASCII}]", "");
	return limpa;
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
            if (matches(linebuff,"<!-- TABELA MEIO -->"))
                read = 1;
            if (read == 1){
                line += linebuff;
            }
            if (matches(linebuff,"<!-- FIM TABELA MEIO -->"))
                read = 0;
        }
        if (source != null) 
            source.close();
        return line;
    }

    public static Boolean matches(String str, String patt){
        if(str.length() == patt.length()){
            for(int i=0; i< str.length(); i++){
                if(str.charAt(i)!= patt.charAt(i))
                    return false;
            }
        }else return false;
        return true;
    }
    
    public static Boolean contains(String str, String patt){
        if (str.length()>patt.length()){
            for(int i=0; i< str.length()-patt.length(); i++){
                if(matches(str.substring(i, i+patt.length()), patt))
                        return true;
            }
        }
        return false;
    }
}