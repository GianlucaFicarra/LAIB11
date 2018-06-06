	package it.polito.tdp.bar;

	import java.net.URL;
	import java.util.ResourceBundle;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.TextArea;

	public class BarController {

		private Simulatore simulazione;
		
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button btnSimula;

	    @FXML
	    private TextArea txtResult;

	    @FXML
	    void doSimulazione(ActionEvent event) {
	    	txtResult.clear();
	    	
	    	Simulatore s= new Simulatore();
			
			s.init();
			s.run();
			
			txtResult.appendText(String.format("Arrivati clienti: %d\nClienti soddisfatti: %d\nClienti Insoddisfatti: %d",
					s.getNumero_totale_client(), s.getNumero_clienti_soddisfatti(), s.getNumero_clienti_insoddisfatti()));
	    }

	    @FXML
	    void initialize() {
	        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bar.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";

	    }

		public void setModel(Simulatore model) {
			this.simulazione=model;
			
		}
	    
	    
	}

