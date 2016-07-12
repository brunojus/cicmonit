package br.unb.cicmonit.bolsas;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class AlocaBolsas {

	private List<String> linhas;
	private HashMap<Integer, Turma> turmas;
	private HashMap<Integer, Candidato> candidatos;
	private int totalBolsas;
	private final String TEMP = "temp.sql";
	private final String RSLT = "result.sql";
	
	private String string;
	private Scanner scanner;
	
	public AlocaBolsas() {
		turmas = new HashMap<Integer, Turma>();
		candidatos = new HashMap<Integer, Candidato>();
	}
	
	public HashMap<Integer, Turma> inicializa() throws IOException {
		readLinhas();
		getTurmas();
		getCandidatos();
		getEloCandTurmas();
		
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
	
	public HashMap<Integer, Turma> getTurmas() {
		Turma t;
		
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(linhas.get(s).contains(Turma.TURMAS)) {
				t = new Turma();
				string = linhas.get(s).substring(Turma.TURMAS_LENGTH).replace(')', ',').replace(", ", ",");
				scanner = new Scanner(string);
				scanner.useDelimiter(",");
				t.setId(scanner.nextInt());
				t.setNome(scanner.next());
				t.setDisciplinaId(scanner.nextInt());
				t.setAlunosMatriculados(scanner.nextInt());
				t.setObrigatoria(scanner.nextInt() == 1);
				turmas.put(t.getId(), t);
				linhas.remove(s);
				scanner.close();
			}
		}
		
		return turmas;
	}
	
	public HashMap<Integer, Turma> getCandidatos() {
		Candidato c;
		
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(linhas.get(s).contains(Candidato.CANDIDATOS)) {
				c = new Candidato();
				string = linhas.get(s).substring(Candidato.CANDIDATOS_LENGTH).replace(')', ',');
				scanner = new Scanner(string);
				scanner.useDelimiter(",");
				c.setId(scanner.nextInt());
				c.setMencao(scanner.next());
				c.setAlunoId(scanner.nextInt());
				c.setAvaliacao(scanner.nextInt());
				candidatos.put(c.getId(), c);
				linhas.remove(s);
				scanner.close();
			}
		}
		
		return turmas;
	}
	
	private void getEloCandTurmas() {
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(linhas.get(s).contains(Candidato.CANDIDATOS_TURMAS)) {
				string = linhas.get(s).substring(Candidato.CANDIDATOS_TURMAS_LENGTH).replace(')', ',');
				scanner = new Scanner(string);
				scanner.useDelimiter(",");
				ligaCandidatoTurmas(scanner.nextInt(), scanner.nextInt());
				linhas.remove(s);
				scanner.close();
			}
		}
	}
	
	private void ligaCandidatoTurmas(Integer cand, Integer turma) {
		Turma t = turmas.get(turma);
		Candidato c = candidatos.get(cand);
		t.getCandidatos().add(c);
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
