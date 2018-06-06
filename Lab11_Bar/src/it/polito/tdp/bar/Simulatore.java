package it.polito.tdp.bar;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


public class Simulatore {

	enum EventType { //equivqle a raccolta di costanti sotto un nome unico (il tipo)
		ARRIVO, // arriva un nuovo gruppo cliente
		PARTENZA // parte gruppo cliente
	}
	
	//parametri
	private int NUMERO_SIMULAZIONI= 2000;
	
	private int time;
	private int numPersone;
	private int durata;
	private double tolleranza;

	
	//modello del mondo
	List<Tavolo> listaTavoli= new LinkedList<>();
	List<GruppoClienti> listaclienti= new LinkedList<>();
	
	
	//Valori in output (da calcolare)
	//private Statistiche statistiche;
	private int numero_totale_client;
	private int numero_clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;
	
	// Coda degli eventi
	PriorityQueue<Event> queue = new PriorityQueue<>();

	
	
	
	//FUNZIONI DEL SIMULATORE:
	
	public void init() { // inizializza la coda degli eventi
		
	//imposto i tavoli a mano ad ogni inizializzazione
	listaTavoli.add(new Tavolo(1,10));
	listaTavoli.add(new Tavolo(2,10));
	
	listaTavoli.add(new Tavolo(3,8));
	listaTavoli.add(new Tavolo(4,8));
	listaTavoli.add(new Tavolo(5,8));
	listaTavoli.add(new Tavolo(6,8));
	
	listaTavoli.add(new Tavolo(7,6));
	listaTavoli.add(new Tavolo(8,6));
	listaTavoli.add(new Tavolo(9,6));
	listaTavoli.add(new Tavolo(10,6));
	
	listaTavoli.add(new Tavolo(11,4));
	listaTavoli.add(new Tavolo(12,4));
	listaTavoli.add(new Tavolo(13,4));
	listaTavoli.add(new Tavolo(14,4));
	listaTavoli.add(new Tavolo(15,4));
	
	// inizializza la coda degli eventi perche dopo la prima simulazione sara piena
	 queue.clear();
				
	 for(int i=0; i<NUMERO_SIMULAZIONI; i++) {//deve generare evento ogni 10 minuti(detto nel testo)
					
		 //valori random qualcono per tutto questo giro di for
					time=  (1+(int)(Math.random()*10)); //L’intervallo tra 1 e 10 minuti;
					 numPersone=  (1+(int)(Math.random()*10)); //num persone tra 1 e 10
					 durata=  60*(1+(int)(Math.random()*2)); //permanenza o 1 o 2 ore
					 tolleranza=  Math.random(); //da 0.0 soddisfatti al tavolo a 0.9 al bancone
						 //--> Math.random() returns a double value 0.0 <x< 1.0. 
							
					GruppoClienti g= new GruppoClienti(i+1,  numPersone, time, durata, tolleranza);
					
					     //time uguale per tutto il gruppo, indica l arrivo
					Event e1 = new Event(time, EventType.ARRIVO, g) ;
								
					queue.add(e1) ;  //aggiungo finche non rggiungo la durata max
				
				
				numero_totale_client=0;
				numero_clienti_soddisfatti=0;
				numero_clienti_insoddisfatti=0;
							
				}
	}
	
	
	
	public void run() {
		Event e ;
		while((e = queue.poll()) != null) { //.poll restituisce e cancella la testa della coda
			processEvent(e) ;
		}
	}
	
	
	 
	private void processEvent(Event e) {
		System.out.println(e);//stampa di debug
		
		switch(e.getTipo()) { 
		
		case ARRIVO:  //evento arrivo cliente
			numero_totale_client+=e.getGruppo().getNumero();
			
			//in base al numero del gruppo valuto se un tavolo li può fare accomodare
            List <Tavolo> tavoliDisponibili = tavoloLibero(e.getGruppo().getNumero());
			
			if (!tavoliDisponibili.isEmpty()) { //se ho tavoli per il gruppo
								
            //il tavolo deve essere pero con il num di posti minimo per ospitarli
            //se sono 6 meglio tavolo da 8 che da 10!!!!!!!!
				   Tavolo t = this.tavoloPiuPiccolo(tavoliDisponibili);
				
				
					//cliente si siede al tavolo: creo evento di uscita
					Event e1 = new Event(e.getMinuti()+e.getGruppo().getDurata(), EventType.PARTENZA, e.getGruppo());
					queue.add(e1) ; 
					
					t.occupa(); //cambia il booleano del tavolo in occupato
					e.getGruppo().setTavolo(t);//assegno tavolo al gruppo
					
					numero_clienti_soddisfatti+=e.getGruppo().getNumero();
					break;
				}
			
			else {
					/*se cliente non trova tavolo valuto la sua tollerabilita:
					 * se 0.0 cliente va via ed è insoddisfatto, altrimenti,
					 * valuto la prob di avere una tolleranza dentro il mio limite di tollerabilità del gruppo
					 * ovvero gruppo ha tolleranza 0.9, valuto la prob di trovarmi entro quel 0.9 
					 * e quindi restare soddisfatto, e la prob di non rientrarci e quindi restare insoddisfatto
					 */
			
				if(e.getGruppo().getTolleranza() < Math.random()) {
					//se casualmente mi incazzo e vado oltre la mia tollerabilità sono insoddisfatto
					numero_clienti_insoddisfatti+= e.getGruppo().getNumero();
				} else {
					//se casualmente non mi incazzo e vado oltre la mia tollerabilità sono soddisfatto
					numero_clienti_soddisfatti+=e.getGruppo().getNumero();
				}
			}	
			break;
			
		case PARTENZA: // evento partenza gruppo
			e.getGruppo().getTavolo().libera();
			break;
		}
		
		
	}
	
	
	//metodi chiamati da processEvent per selezionare il tavolo
	private List<Tavolo> tavoloLibero(int numPersone) {
		List <Tavolo> disponibili = new ArrayList<>();
		for(Tavolo t: listaTavoli) {
			//valuto se tavolo libero
			if(t.isOccupato()==false && t.getNumPosti()>=numPersone
					&& numPersone>0.5*t.getNumPosti()) { //num clienti deve essere maggiore della meta
				disponibili.add(t);
			}
		}
		return disponibili;
	}
	
	
	private Tavolo tavoloPiuPiccolo(List<Tavolo> tavoliDisponibili) {
		int min = Integer.MAX_VALUE;
		Tavolo tavolo = null;
		for (Tavolo t : tavoliDisponibili)
			if (t.getNumPosti() < min) {
				min = t.getNumPosti();
				tavolo = t;
			}
		return tavolo;
	}
	

	public String SimulationResult() {
		return "Arrivati clienti: " + numero_totale_client + 
				"\nClienti soddisfatti: " + numero_clienti_soddisfatti + 
				"\nClienti Insoddisfatti:" + numero_clienti_insoddisfatti;
	}
	
	
}
