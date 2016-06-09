package getlogin;

/**
 * Classe que recebe os dados de um aluno.
 * A ideia é chamar este objeto quando a autenticação for confirmada.
 * 
 * @author Christian
 */
public class Aluno {

	private String nome;
	private int semestre;
	private float ira;
	private String matricula;
	

	String getNome() {
		return nome;
	}
	
	void setNome(String nome) {
		this.nome = nome;
	}
	
	int getSemestre() {
		return semestre;
	}
	
	void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	float getIra() {
		return ira;
	}
	
	void setIra(float ira) {
		this.ira = ira;
	}
	

	String getMatricula() {
		return matricula;
	}

	void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
