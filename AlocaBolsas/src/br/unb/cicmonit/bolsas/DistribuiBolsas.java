package br.unb.cicmonit.bolsas;

import java.util.ArrayList;
import java.util.List;

public class DistribuiBolsas {

	private static LeDados dados;
	private static List<Turma> turmasAlocadas;
	
	public static void main(String[] args) {
		dados = new LeDados();
		turmasAlocadas = new ArrayList<Turma>();
		dados.inicializa();
		
		distribui();

		
		EscreveResultado out = new EscreveResultado(dados);
		out.Escrever();
		
	}
	
	
	public static void distribui() {
		distribuiBolsas(true);
	}
	
	private static void distribuiBolsas(boolean obrigatoria) {
		Turma t = dados.fetchTurmaPriority(obrigatoria);
		
		if(LeDados.getBolsas().bolsasRestantes() == 0) {
			setCandidatosBolsistas();
			return;
		}
		if(t == null) {
			resetTurmasAlocadas(obrigatoria);
			distribuiBolsas(!obrigatoria);
		}
		else if(t.isObrigatoria() == obrigatoria) {
			t.fetchMelhorOpcao().setBolsista();
			LeDados.getBolsas().alocaBolsa();
			t.setAlocado(true);
			turmasAlocadas.add(t);
			distribuiBolsas(obrigatoria);
			t.setAlocado(false);
		}
	}
	
	private static void setCandidatosBolsistas() {
		for(Candidato c : LeDados.getCandidatos().values()) {
			if(c.isBolsista()) {
				if(c.getAvaliacao() == 3)
					c.setAvaliacao(2);
				else if(c.getAvaliacao() == 2)
					c.setAvaliacao(2);
			}
			else {
				if(c.getAvaliacao() == 2)
					c.setAvaliacao(0);
				else if(c.getAvaliacao() == 3)
					c.setAvaliacao(1);
			}
		}
	}
	
	private static void resetTurmasAlocadas(boolean obrigatoria) {
		for(Turma t : turmasAlocadas) {
			if(t.isObrigatoria() == obrigatoria)
				t.setAlocado(false);
		}
	}
	
}
