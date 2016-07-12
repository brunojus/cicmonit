package br.unb.cicmonit.bolsas.testes;

//import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import br.unb.cicmonit.bolsas.*;


public class TesteAlocaBolsas {

	@Test
	public void readTest() throws IOException {
		AlocaBolsas ab = new AlocaBolsas();
		List<Turma> turma;
		for(String s : ab.readLinhas()) {
//			System.out.println(s);
		}
		
		turma = ab.inicializa();
		
		for(Turma t : turma) {
			System.out.println(t.getId() + ", " + t.getNome() + ", " + 
					 t.getAlunosMatriculados() + ", " + t.getDisciplinaId() + ", " + t.isObrigatoria());
			if(t.getCandidatos() != null) {
				for(Candidato c : t.getCandidatos()) {
					System.out.print(c.getAlunoId() + ", ");
				}
				System.out.println("--");
			}
			
		}
		
	}

}
