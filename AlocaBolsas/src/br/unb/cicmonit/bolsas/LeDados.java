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
			readLinhas();
			fetchTurmas();
			fetchCandidatos();
			fetchEloCandTurmas();
			fetchTotalBolsas();
			
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
		for(int s = linhas.size()-1; s >= 0; s--) {
			if(!linhas.get(s).contains("INSERT INTO ")) {
				linhas.remove(s);
			}
		}
		return linhas;
	}
	
	public HashMap<Integer, Turma> fetchTurmas() {
		Turma t;

		if(linhas != null && linhas.size() > 0)
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
					if(t.isObrigatoria())
						Turma.addObrigatorias();
					else
						Turma.addOptativas();
					turmas.put(t.getId(), t);
					linhas.remove(s);
					scanner.close();
				}
			}
		
		return turmas;
	}
	
	public Turma fetchTurmaPriority() {
		Turma turma = null;
		
		for(Turma t : turmas.values()) {
			if(turma == null && t != null)
				turma = t;
			else if((turma.getAlunosMatriculados() < t.getAlunosMatriculados())
					&& t.getBolsasAlocadas() < t.getBolsasRequeridas())
				turma = t;
		}
		
		return turma;
	}
	
	public HashMap<Integer, Turma> fetchCandidatos() {
		Candidato c;

		if(linhas != null && linhas.size() > 0)
			for(int s = linhas.size()-1; s >= 0; s--) {
				if(linhas.get(s).contains(Candidato.CANDIDATOS)) {
					c = new Candidato();
					string = linhas.get(s).substring(Candidato.CANDIDATOS_LENGTH).replace(')', ',');
					scanner = new Scanner(string);
					scanner.useDelimiter(",");
					c.setId(scanner.nextInt());
					c.setMencao(scanner.next().substring(1, 3));
					c.setAlunoId(scanner.nextInt());
					c.setAvaliacao(scanner.nextInt());
					candidatos.put(c.getId(), c);
					linhas.remove(s);
					scanner.close();
				}
			}
		
		return turmas;
	}
	
	public void fetchEloCandTurmas() {
		if(linhas != null && linhas.size() > 0)
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
		if(t != null && c != null) {
			t.getCandidatos().add(c);
			c.setTurmaId(turma);
		}
	}
	
	public void fetchTotalBolsas() {
		if(linhas != null && linhas.size() > 0)
			for(int s = linhas.size()-1; s >= 0; s--) {
				if(linhas.get(s).contains(Bolsas.TOTAL_BOLSAS)) {
					string = linhas.get(s).substring(Bolsas.TOTAL_BOLSAS_LENGTH).replace(')', ',');
					scanner = new Scanner(string);
					scanner.useDelimiter(",");
					bolsas.setId(scanner.nextInt());
					bolsas.setTotalBolsas(scanner.nextInt());
					bolsas.setBolsasAlocadas(scanner.nextInt());
					linhas.remove(s);
					scanner.close();
					return;
				}
				else System.out.println("Nao vi nada u.u");
			}
	}

	public HashMap<Integer, Turma> getTurmas() {
		return turmas;
	}

	public List<Turma> getTurmasList() {
		List<Turma> turma = new ArrayList<Turma>();
		
		
		return turma;
	}
	
	
	public Turma getTurma(int index) {
		return turmas.get(index);
	}

	public HashMap<Integer, Candidato> getCandidatos() {
		return candidatos;
	}

	public Bolsas getBolsas() {
		return bolsas;
	}

	
	
}
