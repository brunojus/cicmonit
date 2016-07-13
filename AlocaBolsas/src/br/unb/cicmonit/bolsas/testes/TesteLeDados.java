package br.unb.cicmonit.bolsas.testes;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import br.unb.cicmonit.bolsas.*;


public class TesteLeDados {

	@Test
	public void readTest() throws IOException {
		LeDados ab = new LeDados();
		HashMap<Integer, Turma> turma;
		
		
		turma = ab.inicializa();

		Assert.assertNotNull(turma);
		Assert.assertEquals(4, turma.size());
		Assert.assertEquals(4, LeDados.getTurmas().size());
		Assert.assertEquals(4, LeDados.getCandidatos().size());
		Assert.assertEquals(2, LeDados.getTurmas().get(2).getCandidatos().size());
		Assert.assertEquals(0, LeDados.getBolsas().getBolsasAlocadas());
		Assert.assertEquals(2, LeDados.getBolsas().getTotalBolsas());
		
	}
	
	@Test
	public void dataTest() {
		LeDados dados = new LeDados();
		dados.inicializa();
		
		Assert.assertEquals(0, LeDados.getTurmas().get(1).getBolsasRequeridas());
		Assert.assertEquals(2, LeDados.getTurmas().get(2).getBolsasRequeridas());
		Assert.assertEquals(1, LeDados.getTurmas().get(3).getBolsasRequeridas());
		Assert.assertEquals(1, LeDados.getTurmas().get(4).getBolsasRequeridas());
		
		Assert.assertEquals(2, Turma.getOptativas());		
		Assert.assertEquals(2, Turma.getObrigatorias());
	}

}
