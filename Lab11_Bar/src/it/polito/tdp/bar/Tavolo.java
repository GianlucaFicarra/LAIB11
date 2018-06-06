package it.polito.tdp.bar;

public class Tavolo {
	
	private int id;
	private int numPosti;
	private boolean occupato;
	
	public Tavolo(int id, int numPosti) {
		super();
		this.id = id;
		this.numPosti = numPosti;
		this.occupato = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumPosti() {
		return numPosti;
	}

	public void setNumPosti(int numPosti) {
		this.numPosti = numPosti;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void libera() {
		this.occupato = false;
	}
	
	public void occupa() {
		this.occupato = true;
	}
	
	

	
}
