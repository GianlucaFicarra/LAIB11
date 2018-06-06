package it.polito.tdp.bar;

public class Model {

	Simulatore sim;
	
	public Model () {
		
		sim = new Simulatore ();
	}
	
	public String simula () {
		sim.init();
		sim.run();
		return sim.SimulationResult();
	}
	
}
