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
 * @author Lincoln
 */
public class navegador {
    //Navega através da arvore de links ,retornando Campus, Departamentos e/ou Matérias
    static void acessodepartamento(String link) throws MalformedURLException{
        String tobewritten = "", Num;
        int flagwriten = 0;
        //condiçao para parar antes de acessar as turmas, pois nelas é outro formato de html
        if (link.contains("oferta_dados.aspx?")){
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
            if(parte1[i].contains("AAO"))
                parte1[i].replace("AAO", "CAO");
            if(parte1[i].contains("AAo"))
                parte1[i].replace("AAo", "cao");
        }
        for(int i = 0; i<parte1.length; i++){
            if(!parte1[i].contains("TABELA MEIO")){
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
        File file = new File("db/db.sql");
        for(int i = 0; i<filtrado.size(); i++){
            //Estas linhas ditam a estrutura inicial do db
            if(link.contains("oferta_dep")&& flagwriten == 0){
            	GetDB.Dep ++;
                tobewritten = "INSERT OR REPLACE INTO \"departamento\" VALUES("+GetDB.Dep+",";
                flagwriten = 1;
            }
            if(link.contains("oferta_campus")&& flagwriten == 0){
            	GetDB.Camp ++;
                tobewritten = "INSERT OR REPLACE INTO \"campus\" VALUES("+GetDB.Camp+",";
                flagwriten = 1;
            }
            if(link.contains("oferta_dis")){
            	GetDB.Dis ++;
                tobewritten = "INSERT OR REPLACE INTO \"disciplinas\" VALUES("+GetDB.Dis+",";
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
                    writer.println(tobewritten+Num+",'"+filtrado.get(i)+"',"+GetDB.Dep+");");
                    flagwriten = 0;
                }
                //Estas linhas são necessárias para navegação a partir de um campus específico
                //devido à estrutura distinta da marcação
                else if(link.contains("oferta_dep")){
                    writer.println(tobewritten+"'"+filtrado.get(i)+"',"+GetDB.Camp+");");
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
        String html, tobewritten, docentes, docentes_turmas, horarioelocal, turma = "", numero_de_alunos = "";
        String [] parte1, parte2, temp;
        List<String> filtrado = new ArrayList<>();
        int k = 0, l;
        boolean check = false;
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
            if(parte1[i].contains("AAO"))
                parte1[i].replace("AAO", "CAO");
            if(parte1[i].contains("AAo"))
                parte1[i].replace("AAo", "cao");
        }
        for(int i = 0; i<parte1.length; i++){
            if(parte1[i].contains("#FFFFFF")&&parte1[i].contains("<b>")&&parte1[i].contains("</b>")){
                filtrado.add(parte1[i].substring(parte1[i].indexOf("<b>")+3,parte1[i].indexOf("</b>")));
            }
            if((parte1[i].contains("Horario:")||parte1[i].contains("Local:"))&&!parte1[i].contains("href=")){
                parte2 = parte1[i].split("</font></td>");
                for(int j = 0; j<parte2.length; j++){
                    if(parte2[j].contains("Horario:")&&parte2[j].contains("Local:")){
                        filtrado.add(parte2[j].substring(parte2[j].indexOf("green>")+6));
                        filtrado.add(parte2[j].substring(parte2[j].indexOf("Horario:")+9,parte2[j].indexOf("Local")));
                        filtrado.add(parte2[j].substring(parte2[j].indexOf("Local:")+7,parte2[j].indexOf("\"><font")));
                    }
                }
            }
            if(parte1[i].contains("smoke><center>")){
                docentes = parte1[i].substring(parte1[i].indexOf("smoke><center>")+14,parte1[i].indexOf("</center>"));
                if(docentes.contains("<br>")){
                    parte2=docentes.split("<br>");
                    for(int j = 0; j<parte2.length; j++){
                        filtrado.add(parte2[j]);
                    }
                }else{
                    filtrado.add(docentes);
                }
            }
            if(parte1[i].contains("Ocupadas")){
            	if (i != k + 3){
            		k = i;
            		filtrado.add(parte1[i].substring(parte1[i].indexOf("</font></b></td>")-2,parte1[i].indexOf("</font></b></td>")));
            	}
            }
            if(parte1[i].contains("Reserva para curso")){
            		filtrado.add("$");            	
            }
        }
        File file = new File("db/db.sql");
        for(int i = 0; i<filtrado.size(); i++){
            //Insere a letra da turma na saida
            //Tratando a única exceção ainda existente
            if(filtrado.get(i).contains("wrap"))
                filtrado.set(i, "DOM");
            if(filtrado.get(i).length()<3){
            	if(filtrado.get(i).contains("$"))
            		i++;
        		if(filtrado.size()<=i)
        			return;
        		turma = filtrado.get(i);
        		numero_de_alunos = filtrado.get(i+1);
        		if(numero_de_alunos.contains(">")){
        			numero_de_alunos = numero_de_alunos.substring(numero_de_alunos.lastIndexOf('>')+1);
        		}
        		l=i+2;
        		while(!check && l<filtrado.size()){
        			try{
        				Integer.parseInt(filtrado.get(l));
        				check = true;
        				l=0;
        			}catch(NumberFormatException exp){
        				if(filtrado.get(l).contains("$")){
        					check = true;
        				}else{
        					l++;
        				}
        			}
        		}
        		if(l!= 0 && l<filtrado.size()){
	            	GetDB.Tur ++;
	                tobewritten = "INSERT OR REPLACE INTO \"turmas\" VALUES("+GetDB.Tur+",'"+turma+"'";
	                tobewritten+=","+GetDB.Dis+","+numero_de_alunos+",1);";
	                l=0;
	                check = false;
	                try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
	                    writer.println(tobewritten);
	                    writer.close();
	                    i++;
	                } catch (IOException ex) {
	                    Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
	                }
        		}else{
        			GetDB.Tur ++;
                    tobewritten = "INSERT OR REPLACE INTO \"turmas\" VALUES("+GetDB.Tur+",'"+turma+"'";
                    tobewritten+=","+GetDB.Dis+","+numero_de_alunos+",0);";
                    check = false;
                    try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                        writer.println(tobewritten);
                        writer.close();
                        i++;
                    } catch (IOException ex) {
                        Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
                    }
        		}
            }else if(filtrado.get(i).length()==3){
                //Dia
            	GetDB.Hor ++;
                horarioelocal="INSERT OR REPLACE INTO \"horarios\" VALUES("+GetDB.Hor+",'"+filtrado.get(i)+" "; 
                i=i+1;
                if(i<filtrado.size()){
                    //Horario
                    horarioelocal+=filtrado.get(i)+" ";
                    i=i+1;
                    if(i<filtrado.size())
                        //Local (Sempre existe, mesmo que seja A definir)
                        horarioelocal+=filtrado.get(i);
                    horarioelocal+="',"+GetDB.Tur+");";
                    try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                        writer.println(horarioelocal);
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Caso tenha dia mas nao tenha horário nem local
                else{
                   horarioelocal+="',"+GetDB.Tur+");";
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
            	docentes = "";
            	temp = new String[2];
            	temp[0] = filtrado.get(i);
            	if(!Professor.exists(temp)){
	            	temp = Professor.addProfessor(temp);
	                docentes = "INSERT OR REPLACE INTO \"docentes\" VALUES("+temp[1]+",'"+temp[0]+"',NULL);";
            	}else{
            		temp = Professor.addProfessor(temp);
            	}
            	docentes_turmas = "INSERT OR REPLACE INTO \"docentes_turmas\" VALUES("+temp[1]+","+GetDB.Tur+");";
                try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                	if(!docentes.isEmpty())
                		writer.println(docentes);
                    writer.println(docentes_turmas);
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
