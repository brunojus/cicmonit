package getlogin;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe que vai verificar a autenticação
 * 
 * @author Christian
 */
public class GetDataAluno {
	
	private Aluno aluno;
	
	public GetDataAluno(){
		aluno = new Aluno();
	}
	
	GetDataAluno(String matricula) {
		this();
		aluno.setMatricula(matricula);
	}
	
	boolean verificaAcesso(String senha) throws LoginException, MalformedURLException {
		
		URL login_url = new URL("https://wwwsec.serverweb.unb.br/matriculaweb/graduacao/sec/login.aspx");
		
		
		
		return true;
	}

}