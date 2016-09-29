// ENTITY_java.vm
package org.salondeventas.desktop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;


public class PanelClientes implements Initializable {

	@FXML
	private TextField txtidclientes;

	@FXML
	private TextField txtnombre;

	@FXML
	private TextField txtapellido;

	@FXML
	private TextField txttelefono;

	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	private void guardar(ActionEvent event) {
	}
}