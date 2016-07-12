package br.unb.cicmonit.bolsas;



public class DistribuiBolsas {

	LeDados dados;
	
	
	public DistribuiBolsas() {
		dados = new LeDados();
		dados.inicializa();
	}
	
	
	public void distribuiBolsas(boolean obrigatoria, int turmaCV) {
		
		//TODO @Chris estou fazendo agora.
		
	}
	
	private void alocaBolsa(Candidato c, Turma t) {
		c.setBolsista();
		t.alocaBolsa();
		dados.getBolsas().alocaBolsa();
	}
	
}
