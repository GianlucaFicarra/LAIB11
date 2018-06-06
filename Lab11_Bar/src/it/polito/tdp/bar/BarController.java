	package it.polito.tdp.bar;

	import java.net.URL;
	import java.util.ResourceBundle;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.TextArea;

	public class BarController {

		private Model model;
		
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
	    	
	    	String result = model.simula(); //avvia simulazione e ritorna il risultato in stringa
	    	if (result != null && result != "")
	    		this.txtResult.appendText(result);
	    	
	    }

	    @FXML
	    void initialize() {
	        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bar.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";

	    }

		public void setModel(Model model) {
			
			this.model = model;
			
		}
	    
	    
	}

