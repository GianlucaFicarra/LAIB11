package it.polito.tdp.bar;

public class GruppoClienti {

	private int id;
	private int numero;
	private int time;
	private int durata;
	private double tolleranza;
	
	private Tavolo tavolo;
	
	public GruppoClienti(int id, int numero, int time, int durata, double tolleranza) {
		super();
		this.id = id;
		this.numero = numero;
		this.time = time;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}

	//tengo traccia del tavolo occupato
	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	
	
}
