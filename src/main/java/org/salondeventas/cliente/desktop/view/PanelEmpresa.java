// ENTITY_java.vm
package org.salondeventas.cliente.desktop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;


public class PanelEmpresa implements Initializable {

	@FXML
	private TextField txtidempresa;

	@FXML
	private TextField txtdescripcion;

	@FXML
	private TextField txtdireccion;


	@FXML
	private TextField txtnombre;

	@FXML
	private TextField txttelefono;

	public PanelEmpresa(PanelGrillaEmpresa panelGrillaEmpresa) {
		// TODO Auto-generated constructor stub
	}

	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	private void guardar(ActionEvent event) {
	}
}