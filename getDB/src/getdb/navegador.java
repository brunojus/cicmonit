/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Linco
 */
public class navegador {
        //Navega através da arvore de links ,retornando Campus, Departamentos e/ou Matérias
        static void spider(String link) throws MalformedURLException{
            //condiçao para parar antes de acessar as turmas, pois nelas é outro formato de html
            //o return sera substituido pela funçao de acesso as turmas
            if (link.contains("oferta_dados.aspx?"))
                return;
            //Url da raiz, todos os links sao somados à essa url
            String startlink = "https://www.matriculaweb.unb.br/matriculaweb/graduacao/";
            URL url = new URL(startlink.concat(link));
            String html;
            String [] parte1, parte2=null;
            List<String> filtrado = new ArrayList<>();
            //Todo o bloco necessário do html é lido como uma string
            try{
                html = getdb.ReadPage.readblock(url);
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
            for(int i = 0; i<filtrado.size(); i++){
                if(filtrado.get(i).contains(">")){
                   parte1 = filtrado.get(i).split(">");
                   filtrado.set(i, parte1[1]);
                   // aqui vai ser adicionado a saida pro banco
                   System.out.println(filtrado.get(i));
                   spider(parte1[0]);
                }//else
                    //esta linha é necessária para navegação a partir do campus, pois há a necessidade
                    //de uma ordem entre números identificadores, siglas e nomes
                    //Aqui seria adicionada a saida pro banco caso o programa operasse em outros campus/departamentos
                    //System.out.println(filtrado.get(i));
            }
        }       
    }
