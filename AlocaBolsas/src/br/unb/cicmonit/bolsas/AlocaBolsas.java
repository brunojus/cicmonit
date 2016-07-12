package br.unb.cicmonit.bolsas;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class AlocaBolsas {

	private List<Turma> turmas;
	private List<String> linhas;
	private List<Candidato> candidatos;
	private int totalBolsas;
	private final String TEMP = "temp.sql";
	private final String RSLT = "result.sql";
	
	private String string;
	private Scanner scanner;
	
	public AlocaBolsas() {
		turmas = new ArrayList<Turma>();
		candidatos = new ArrayList<Candidato>();
	}
	
	public List<Turma> inicializa() throws IOException {
		readLinhas();
		getTurmas();
		getCandidatos();
		
		return turmas;
	}
	
	public List<String> readLinhas() throws IOException {
		Path path = Paths.get(TEMP);
		linhas = Files.readAllLines(path);
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(!linhas.get(s).contains("INSERT INTO ")) {
				linhas.remove(s);
			}
		}
		return linhas;
	}
	
	public List<Turma> getTurmas() {
		Turma t;
		
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(linhas.get(s).contains(Turma.TURMAS)) {
				t = new Turma();
				string = linhas.get(s).substring(Turma.TURMAS_LENGTH);
				scanner = new Scanner(string);
				scanner.useDelimiter(",");
				t.setId(scanner.nextInt());
				t.setNome(scanner.next());
				t.setDisciplinaId(scanner.nextInt());
				t.setAlunosMatriculados(scanner.nextInt());
				t.setObrigatoria(scanner.next().equals(" 1);"));
				turmas.add(t);
				linhas.remove(s);
				scanner.close();
			}
		}
		
		Collections.reverse(turmas);
		return turmas;
	}
	
	public List<Turma> getCandidatos() {
		Candidato c;
		int ind;
		
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(linhas.get(s).contains(Candidato.CANDIDATOS)) {
				c = new Candidato();
				string = linhas.get(s).substring(Candidato.CANDIDATOS_LENGTH);
				scanner = new Scanner(string);
				scanner.useDelimiter(",");
				c.setId(scanner.nextInt());
				c.setMencao(scanner.next());
				c.setAlunoId(scanner.nextInt());
				scanner.useDelimiter(")");
				c.setAvaliacao(scanner.nextInt());
				candidatos.add(c);
				linhas.remove(s);
				scanner.close();
			}
		}
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(linhas.get(s).contains(Candidato.CANDIDATOS_TURMAS)) {
				string = linhas.get(s).substring(Candidato.CANDIDATOS_TURMAS_LENGTH);
				scanner = new Scanner(string);
				scanner.useDelimiter(",");
//				candidatos.
				//TODO @Chris fazendo...

//				candidatos.add(c);
				linhas.remove(s);
				scanner.close();
			}
		}
		
		
		
		
		
		return turmas;
	}
	
	public void distribuiBolsas() {
		
	}
	
	private void alocaBolsa(int id) {
		if(totalBolsas > 0) {
			// TODO aloca uma bolsa para o id
			totalBolsas--;
		}
	}
	
}
