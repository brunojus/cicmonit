package br.unb.cicmonit.bolsas;

public class DistribuiBolsas {

	private static LeDados dados;
	
	public static void Main() {
		dados = new LeDados();
		dados.inicializa();
		
		distribui();
		
		
	}
	
	
	public static void distribui() {
		distribuiBolsas(true);
	}
	
	private static void distribuiBolsas(boolean obrigatoria) {
		Turma t = dados.fetchTurmaPriority(obrigatoria);
		
		if(LeDados.getBolsas().bolsasRestantes() == 0)
			return;
		if(t == null)
			distribuiBolsas(!obrigatoria);
		if(t.isObrigatoria() == obrigatoria) {
			alocaBolsa(t.fetchMelhorOpcao(), t);
			t.setAlocado(true);
			distribuiBolsas(obrigatoria);
			t.setAlocado(false);
		}
	}
	
	private static void alocaBolsa(Candidato c, Turma t) {
		c.setBolsista();
		t.alocaBolsa();
		LeDados.getBolsas().alocaBolsa();
	}
	
}
