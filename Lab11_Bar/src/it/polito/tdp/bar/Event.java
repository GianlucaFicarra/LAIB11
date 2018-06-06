package it.polito.tdp.bar;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.bar.Simulatore.EventType;



public class Event implements Comparable<Event> {

	private int  minuti; //min quando si verifica simulazione
	private EventType tipo ; //tipo di simulazione
	
	private GruppoClienti gruppo;

	
	public Event(int minuti, EventType tipo, GruppoClienti gruppo) {
		super();
		this.minuti = minuti;
		this.tipo = tipo;
		this.gruppo = gruppo;
	}

	
	public int getMinuti() {
		return minuti;
	}


	public EventType getTipo() {
		return tipo;
	}


	public GruppoClienti getGruppo() {
		return gruppo;
	}

	@Override
	public int compareTo(Event other) {
		return this.minuti-other.getMinuti() ;
	}
	
	
}
