package br.unb.cicmonit.bolsas;

import java.util.ArrayList;
import java.util.List;

public class Turma {

	private int id;
	private String nome;
	private int disciplinaId;
	private int alunosMatriculados;
	private boolean obrigatoria;
	
	private List<Candidato> candidatos;
	private int bolsasRequeridas;
	private int bolsasAlocadas;
	private int prioridade;
	private static int obrigatorias = 0;
	private static int optativas = 0;
	private boolean alocado;
	public static final String TURMAS = "\"turmas\"";
	public static final int TURMAS_LENGTH = 28;
	
	
	public Turma() {
		candidatos = new ArrayList<Candidato>();
		alocado = false;
	}
	
	public Turma(int id, String nome, int disciplinaId,
			int alunosMatriculados, boolean obrigatoria) {
		this();
		this.id = id;
		this.nome = nome;
		this.disciplinaId = disciplinaId;
		this.alunosMatriculados = alunosMatriculados;
		this.obrigatoria = obrigatoria;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Turma)
			return ((Turma) o).getId() == this.getId();
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public int getAlunosMatriculados() {
		return alunosMatriculados;
	}

	public void setAlunosMatriculados(int alunosMatriculados) {
		this.alunosMatriculados = alunosMatriculados;
	}

	public boolean isObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	
	public int getBolsasRequeridas() {
		return bolsasRequeridas;
	}

	public void setBolsasRequeridas() {
		bolsasRequeridas = 0;
		for(Candidato c : candidatos)
			if(c.getAvaliacao() == 2 || c.getAvaliacao() == 3) 
				bolsasRequeridas++;
		
	}

	public int getBolsasAlocadas() {
		return bolsasAlocadas;
	}

	public void alocaBolsa() {
		bolsasAlocadas++;
	}
	
	public Candidato fetchMelhorOpcao() {
		Candidato OP = null;
		
		for(Candidato c : candidatos) {
			if(OP == null && c != null)
				OP = c;
			else if((c.getMencao().compareTo(OP.getMencao()) > 0) && !c.isBolsista())
				OP = c;
		}
		
		return OP;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public static int getObrigatorias() {
		return obrigatorias;
	}

	public static void setObrigatorias(int obrigatorias) {
		Turma.obrigatorias = obrigatorias;
	}
	
	public static void addObrigatorias() {
		Turma.obrigatorias++;
	}

	public static int getOptativas() {
		return optativas;
	}

	public static void setOptativas(int optativas) {
		Turma.optativas = optativas;
	}
	
	public static void addOptativas() {
		Turma.optativas++;
	}

	public boolean isAlocado() {
		return alocado;
	}

	public void setAlocado(boolean alocado) {
		this.alocado = alocado;
	}
	
	
}
