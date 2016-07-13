package br.unb.cicmonit.bolsas;

public class Candidato {

	private int id;
	private String mencao;
	private int alunoId;
	private int avaliacao;
	
	private int turmaId;
	private boolean bolsista;
	public final static String CANDIDATOS = "\"candidatos\"";
	public final static String TABLE = "CREATE TABLE \"candidatos\" (\"id\" INTEGER "
			+ "PRIMARY KEY AUTOINCREMENT NOT NULL, \"mencao\" varchar, \"aluno_id\" integer, \"avaliacao\" integer);";
	public final static String CANDIDATOS_TURMAS = "\"candidatos_turmas\"";
	public final static String TABLE_CT = "CREATE TABLE \"candidatos_turmas\" "
			+ "(\"candidato_id\" integer NOT NULL, \"turma_id\" integer NOT NULL);";
	public final static int CANDIDATOS_LENGTH = 32;
	public final static int CANDIDATOS_TURMAS_LENGTH = 39;
	
	public Candidato() {
		bolsista = false;
	}

	public Candidato(int id, String mencao, int alunoId, int avaliacao) {
		this.id = id;
		this.mencao = mencao;
		this.alunoId = alunoId;
		this.avaliacao = avaliacao;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Candidato)
			return ((Turma) o).getId() == this.getId();
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMencao() {
		return mencao;
	}

	public void setMencao(String mencao) {
		this.mencao = mencao;
	}

	public int getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public int getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(int turmaId) {
		this.turmaId = turmaId;
	}

	public boolean isBolsista() {
		return bolsista;
	}
	
	public void setBolsista(boolean bolsista) {
		this.bolsista = bolsista;
	}

	public void setBolsista() {
		bolsista = true;
	}
	
	
}
