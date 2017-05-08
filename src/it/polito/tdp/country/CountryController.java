package it.polito.tdp.country;

import java.net.URL;
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

    }

    @FXML
    void doCercaraggiungibili(ActionEvent event) {

    }

    public void setModel(Model model){
    	this.model=model;
    	
    	//riempi la tendina delle nazione
    	cmbPartenza.getItems().addAll(model.getCountries());
    }
}

