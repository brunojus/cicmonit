package br.unb.cicmonit.bolsas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class LeDados {

	private List<String> linhas;
	private static HashMap<Integer, Turma> turmas;
	private static HashMap<Integer, Candidato> candidatos;
	private static Bolsas bolsas;
	private final String TEMP = "temp.sql";
	
	private String string;
	private Scanner scanner;
	
	public LeDados() {
		turmas = new HashMap<Integer, Turma>();
		candidatos = new HashMap<Integer, Candidato>();
		bolsas = new Bolsas();
		Turma.setObrigatorias(0);
		Turma.setOptativas(0);
	}
	
	public HashMap<Integer, Turma> inicializa() {
		try {
			fetchEloCandTurmas(readLinhas());
			
			for(Turma t : turmas.values()) {
				t.setBolsasRequeridas();
			}
		}
		catch(IOException e) {
			System.out.println("Erro ao encontrar a base de dados!");
			e.printStackTrace();
		}
		
		return turmas;
	}
	
	public List<String> readLinhas() throws IOException {
		Path path = Paths.get(TEMP);
		linhas = Files.readAllLines(path);
		List<String> candidatosETurmas = new ArrayList<String>();
		
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(!linhas.get(s).contains("INSERT INTO ")) {
//				linhas.remove(s);
			}
			else if(linhas.get(s).contains(Turma.TURMAS)) {
				fetchTurmas(linhas.get(s));
				linhas.remove(s);
			}
			else if(linhas.get(s).contains(Candidato.CANDIDATOS)) {
				fetchCandidatos(linhas.get(s));
				linhas.remove(s);
			}
			else if(linhas.get(s).contains(Candidato.CANDIDATOS_TURMAS)) {
				candidatosETurmas.add(linhas.get(s));
			}
			else if(linhas.get(s).contains(Bolsas.TOTAL_BOLSAS)) {
				fetchTotalBolsas(linhas.get(s));
				linhas.remove(s);
			}
		}
		return candidatosETurmas;
	}
	
	public void fetchTurmas(String string) {
		Turma t = new Turma();
		
		string = string.substring(Turma.TURMAS_LENGTH).replace(')', ',').replace(", ", ",");
		scanner = new Scanner(string);
		scanner.useDelimiter(",");
		t.setId(scanner.nextInt());
		t.setNome(scanner.next());
		t.setDisciplinaId(scanner.nextInt());
		t.setAlunosMatriculados(scanner.nextInt());
		t.setObrigatoria(scanner.nextInt() == 1);
		if(t.isObrigatoria())
			Turma.addObrigatorias();
		else
			Turma.addOptativas();
		turmas.put(t.getId(), t);
		scanner.close();
	}
	
	public Turma fetchTurmaPriority(boolean obrigatoria) {
		Turma turma = null;
		int qtd = 0;
		
		for(Turma t : turmas.values()) {
			if(t.isObrigatoria() == obrigatoria) {
				
				if(t.getAlunosMatriculados() >= qtd && !t.isAlocado() && (t.getBolsasRequeridas() > 0)) {
					qtd = t.getAlunosMatriculados();
					turma = t;
				}
			}
		}
		
		return turma;
	}
	
	public void fetchCandidatos(String string) {
		Candidato c = new Candidato();
		
		string = string.substring(Candidato.CANDIDATOS_LENGTH).replace(')', ',');
		scanner = new Scanner(string);
		scanner.useDelimiter(",");
		c.setId(scanner.nextInt());
		c.setMencao(scanner.next().substring(1, 3));
		c.setAlunoId(scanner.nextInt());
		c.setAvaliacao(scanner.nextInt());
		candidatos.put(c.getId(), c);
		scanner.close();
	}
	
	public void fetchEloCandTurmas(List<String> candETurmas) {
		if(candETurmas != null && candETurmas.size() > 0)
			for(int s = candETurmas.size()-1; s >= 0; s--) {
				if(candETurmas.get(s).contains(Candidato.CANDIDATOS_TURMAS)) {
					string = candETurmas.get(s).substring(Candidato.CANDIDATOS_TURMAS_LENGTH).replace(')', ',');
					scanner = new Scanner(string);
					scanner.useDelimiter(",");
					ligaCandidatoTurmas(scanner.nextInt(), scanner.nextInt());
					candETurmas.remove(s);
					scanner.close();
				}
			}
	}
	
	private void ligaCandidatoTurmas(Integer cand, Integer turma) {
		Turma t = turmas.get(turma);
		Candidato c = candidatos.get(cand);
		if(t != null && c != null) {
			t.getCandidatos().add(c);
			c.setTurmaId(turma);
		}
	}
	
	public void fetchTotalBolsas(String string) {
		string = string.substring(Bolsas.TOTAL_BOLSAS_LENGTH).replace(')', ',');
		scanner = new Scanner(string);
		scanner.useDelimiter(",");
		bolsas.setId(scanner.nextInt());
		bolsas.setTotalBolsas(scanner.nextInt());
		bolsas.setBolsasAlocadas(scanner.nextInt());
		scanner.close();
	}

	public static HashMap<Integer, Turma> getTurmas() {
		return turmas;
	}
	
	public Turma getTurma(int index) {
		return turmas.get(index);
	}

	public static HashMap<Integer, Candidato> getCandidatos() {
		return candidatos;
	}

	public static Bolsas getBolsas() {
		return bolsas;
	}
	
}
