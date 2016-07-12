package br.unb.cicmonit.bolsas;

import java.util.List;

public class Turma {

	private int id;
	private String nome;
	private int disciplinaId;
	private int alunosMatriculados;
	private boolean obrigatoria;
	private int bolsasRequeridas;
	private List<Candidato> candidatos;
	public static final String TURMAS = "\"turmas\"";
	public static final int TURMAS_LENGTH = 28;
	
	
	public Turma() {}
	
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

	public int getBolsasDisponiveis() {
		return bolsasRequeridas;
	}

	public void setBolsasDisponiveis(int bolsasDisponiveis) {
		this.bolsasRequeridas = bolsasDisponiveis;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	
}
