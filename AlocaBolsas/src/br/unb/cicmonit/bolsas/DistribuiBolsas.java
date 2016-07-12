package br.unb.cicmonit.bolsas;

import java.util.List;

public class DistribuiBolsas {

	LeDados dados;
	List<Turma> turmas;
	
	public DistribuiBolsas() {
		dados = new LeDados();
		dados.inicializa();
		turmas = dados.getTurmasList();
	}
	
	
	public void distribui() {
		distribuiBolsas(true, 1);
	}
	
	private void distribuiBolsas(boolean obrigatoria, int turmaCV) {
		if(dados.getBolsas().bolsasRestantes() == 0)
			return;
		if(turmas.get(turmaCV).isObrigatoria() == obrigatoria) {
			
			
			
		}
		if(turmaCV == turmas.size())
			distribuiBolsas(false, 1);
	}
	
	private void alocaBolsa(Candidato c, Turma t) {
		c.setBolsista();
		t.alocaBolsa();
		dados.getBolsas().alocaBolsa();
	}
	
}
