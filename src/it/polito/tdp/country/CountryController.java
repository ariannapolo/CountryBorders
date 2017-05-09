package it.polito.tdp.country;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.country.model.Country;
import it.polito.tdp.country.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;


public class CountryController {
	Model model;

    @FXML
    private ComboBox<Country> cmbPartenza;

    @FXML
    private Button btnCercaRaggiungibili;

    @FXML
    private ComboBox<Country> cmbDestinazione;

    @FXML
    private Button btnCercaPercorso;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaPercorso(ActionEvent event) {
    	Country destinazione = cmbDestinazione.getValue();
    	List<Country> percorso = model.getPercorso(destinazione);
    	txtResult.appendText(percorso.toString());

    }

    @FXML
    void doCercaraggiungibili(ActionEvent event) {
    	Country partenza = cmbPartenza.getValue();
    	if(partenza ==null){
    		txtResult.appendText("Errore: devi selezionare lo stato di partenza");
    		return;
    	}
    	List<Country> raggiungibili = model.getRaggiungibili(partenza);
    	txtResult.appendText(raggiungibili.toString());
    	//riempo la seconda tendina
    	cmbDestinazione.getItems().addAll(raggiungibili);
    }

    public void setModel(Model model){
    	this.model=model;
    	
    	//riempi la tendina delle nazione
    	cmbPartenza.getItems().addAll(model.getCountries());
    }
}

