package br.unb.cicmonit.bolsas;

public class Bolsas {

	private int id;
	private int totalBolsas;
	private int bolsasAlocadas;
	public final static String TOTAL_BOLSAS = "\"total_de_bolsas\"";
	public final static String TABLE = "CREATE TABLE \"total_de_bolsas\" (\"id\""
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \"QTE\" integer, \"QTE_alocadas\" integer)";
	public final static int TOTAL_BOLSAS_LENGTH = 37;
	
	public Bolsas() {}

	public int getTotalBolsas() {
		return totalBolsas;
	}

	public void setTotalBolsas(int totalBolsas) {
		this.totalBolsas = totalBolsas;
	}

	public int getBolsasAlocadas() {
		return bolsasAlocadas;
	}

	public void setBolsasAlocadas(int bolsasAlocadas) {
		this.bolsasAlocadas = bolsasAlocadas;
	}
	
	public void alocaBolsa() {
		bolsasAlocadas++;
	}
	
	public int bolsasRestantes() {
		return totalBolsas - bolsasAlocadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
